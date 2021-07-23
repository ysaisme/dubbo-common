package com.ysa.common.error;

import com.ysa.common.model.YsaBaseResponse;
import com.ysa.common.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ysa
 * @date 2020/4/3 16:41
 * @description
 */
@ControllerAdvice
@Slf4j
public class YsaExceptionHandler {

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public YsaBaseResponse argumentMissingError(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        YsaBaseResponse ysaBaseResponse = new YsaBaseResponse();
        ysaBaseResponse.setCode(YsaServiceExceptionEnum.PARAM_ERROR.getValue());
        ysaBaseResponse.setMsg("参数错误");
        return ysaBaseResponse;
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public YsaBaseResponse bindError(HttpServletRequest request, HttpServletResponse response, Object handler, BindException ex) {
        String errMessage = ex.getFieldErrors().stream().map(fieldError -> fieldError.getField() + ":" + fieldError.getDefaultMessage()).collect(Collectors.joining(","));
        YsaBaseResponse ysaBaseResponse = new YsaBaseResponse();
        ysaBaseResponse.setCode(YsaServiceExceptionEnum.PARAM_ERROR.getValue());
        ysaBaseResponse.setMsg(errMessage);
        log.warn("server param error ", ex);
        return ysaBaseResponse;

    }


    /**
     * 抓取所有的错误
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public YsaBaseResponse defaultErrorHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        YsaBaseResponse ysaBaseResponse = new YsaBaseResponse();
        ysaBaseResponse.setCode(YsaServiceExceptionEnum.SERVICE_ERROR.getValue());
        ysaBaseResponse.setMsg("服务器繁忙");
        log.error("server error ", ex);
        return ysaBaseResponse;
    }


    /**
     * 所有的业务错误
     *
     * @param request
     * @param response
     * @param handler
     * @param ysaServiceException
     * @return
     */
    @ResponseBody
    @ExceptionHandler(YsaServiceException.class)
    public YsaBaseResponse payExceptionHandle(HttpServletRequest request, HttpServletResponse response, Object handler, YsaServiceException ysaServiceException) {
        YsaBaseResponse ysaBaseResponse = new YsaBaseResponse();
        ysaBaseResponse.setCode(ysaServiceException.getYsaServiceExceptionEnum().getValue());
        ysaBaseResponse.setMsg(Optional.ofNullable(ysaServiceException.getErrorDesc()).orElse("服务器繁忙"));
        if (DataUtils.isNotEmpty(ysaServiceException.getData())) {
            ysaBaseResponse.setData(ysaServiceException.getData());
        }
        log.debug("business err {} ", ysaServiceException.getErrorDesc());
        return ysaBaseResponse;
    }

}
