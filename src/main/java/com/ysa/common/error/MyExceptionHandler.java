package com.ysa.common.error;


import com.ysa.common.model.BaseResult;
import com.ysa.common.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.utils.CollectionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    /**
     * 处理自定义项目异常
     *
     * @param e 异常
     * @return result
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public BaseResult processOrderException(BusinessException e) {
        BaseResult result = BaseResult.fail(e.getMessage());
        log.info("业务逻辑异常: {} {}", result.getMessage(), e.getMessage());
        return result;
    }

    /**
     * 处理自定义项目异常
     *
     * @param e 异常
     * @return result
     */
    @ExceptionHandler(YsaServiceException.class)
    @ResponseBody
    public BaseResult ysaServiceException(YsaServiceException e) {
        BaseResult result = BaseResult.fail(e.getMessage());
        log.info("业务逻辑异常: {} {}", result.getMessage(), e.getMessage());
        return result;
    }

    /**
     * 处理自定义项目异常
     *
     * @param e 异常
     * @return result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResult processOrderException(Exception e) {
        log.error("系统异常： {}", e.getMessage(), e);
        return BaseResult.systemError();
    }

    /**
     * 处理自定义项目异常
     *
     * @param e 异常
     * @return result
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public BaseResult processHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("处理springmvc接收参数类型异常:" + e.getMessage(), e);
        return BaseResult.fail(DataUtils.isNotEmpty(e.getRootCause()) ? e.getRootCause().getMessage() : e.getMessage());
    }


    /**
     * 处理http请求方式不支持异常
     *
     * @return result
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public BaseResult processDuplicateKeyException(HttpRequestMethodNotSupportedException e) {
        log.info("处理http请求方式不支持异常：{}", e.getMessage());
        return BaseResult.systemError();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> list = e.getBindingResult().getAllErrors();
        String message = "绑定参数异常";
        if (CollectionUtils.isNotEmpty(list)) {
            message += list.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        }
        log.info("绑定参数异常 {}", message);
        return BaseResult.fail(message);
    }

}
