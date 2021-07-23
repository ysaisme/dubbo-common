package com.ysa.common.error;

/**
 * @author ysa
 * @date 2020/4/17 15:19
 * @description
 */

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;

/**
 * 业务错误
 *
 * @author ysa
 */
@Getter
@Setter
public class YsaServiceException extends RuntimeException {

    /**
     * 错误类型
     */
    private YsaServiceExceptionEnum ysaServiceExceptionEnum;

    /**
     * 错误的描述
     */
    private String errorDesc;

    /**
     * 需要返回的数据
     */
    private Object data;

    public YsaServiceException(String desc) {
        super(desc);
        this.ysaServiceExceptionEnum = ysaServiceExceptionEnum;
        this.errorDesc = desc;
    }

    public YsaServiceException(YsaServiceExceptionEnum ysaServiceExceptionEnum, String desc) {
        super(desc);
        this.ysaServiceExceptionEnum = ysaServiceExceptionEnum;
        this.errorDesc = desc;
    }

    public YsaServiceException(YsaServiceExceptionEnum ysaServiceExceptionEnum, String desc, Object data) {
        super(desc);
        this.ysaServiceExceptionEnum = ysaServiceExceptionEnum;
        this.errorDesc = desc;
        this.data = data;
    }
}
