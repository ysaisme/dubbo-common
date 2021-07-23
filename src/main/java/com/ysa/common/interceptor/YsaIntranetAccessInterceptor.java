package com.ysa.common.interceptor;

import com.ysa.common.annotation.YsaIntranetAccess;
import com.ysa.common.error.YsaNoPermissionException;
import com.ysa.common.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author ysa
 * @date 2020/5/7 17:49
 * @description
 */
@Slf4j
public class YsaIntranetAccessInterceptor implements HandlerInterceptor {
    private static final String INTERCEPT_PATTERN = "ms-origin";
    private static final String INTERCEPT_PATTERN_HEADER = "ms-admin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (DataUtils.isNotEmpty(handler) && handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            //表示存在内网访问
            if (method.isAnnotationPresent(YsaIntranetAccess.class)) {
                if (DataUtils.isNotEmpty(request.getHeader(INTERCEPT_PATTERN_HEADER))) {
                    return true;
                }
                //这个表示是从网关转过来的请求 ,其他的还没有应用
                if (DataUtils.isNotEmpty(request.getHeader(INTERCEPT_PATTERN))) {
                    throw new YsaNoPermissionException("没有权限访问");
                }
            }
        }
        return true;
    }
}
