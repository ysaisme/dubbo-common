package com.ysa.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ysa.common.annotation.YsaResponseBody;
import com.ysa.common.model.YsaBaseResponse;
import com.ysa.common.util.DataUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.AsyncHandlerMethodReturnValueHandler;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author ysa
 * @date 2020/3/28 13:53
 * @description 这个是包装所有的返回的json数据
 */
public class YsaResponseBodyHandleReturnValue implements HandlerMethodReturnValueHandler, AsyncHandlerMethodReturnValueHandler {
    /**
     * 处理所有非异常的错误
     *
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        //如果已经是基础的返回值
        return returnType.getParameterType() != YsaBaseResponse.class
                && DataUtils.isNotEmpty(returnType.getAnnotatedElement().getAnnotation(YsaResponseBody.class));
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        assert response != null;
        response.setContentType("application/json;charset=utf-8");
        YsaBaseResponse baseResponse = new YsaBaseResponse();
        baseResponse.setCode(100);
        baseResponse.setMsg("成功");
        baseResponse.setData(returnValue);

        YsaResponseBody ysaResponseBody = returnType.getAnnotatedElement().getAnnotation(YsaResponseBody.class);

        SerializerFeature[] defaultSerializerFeature = {
                SerializerFeature.DisableCircularReferenceDetect
        };
        /**
         * 替换注解中的序列化格式
         */
        if (DataUtils.isNotEmpty(ysaResponseBody) && DataUtils.isNotEmpty(ysaResponseBody.serializerFeature())) {
            defaultSerializerFeature = ysaResponseBody.serializerFeature();
        }
        response.getWriter().write(JSON.toJSONString(baseResponse, defaultSerializerFeature));

    }

    @Override
    public boolean isAsyncReturnValue(Object returnValue, MethodParameter returnType) {
        return supportsReturnType(returnType);
    }
}
