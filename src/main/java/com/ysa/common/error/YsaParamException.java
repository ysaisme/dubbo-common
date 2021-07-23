package com.ysa.common.error;

/**
 * @author ysa
 * @date 2020/4/17 15:19
 * @description
 */

import lombok.Getter;
import lombok.Setter;

/**
 * 参数错误
 *
 * @author ysa
 */
@Getter
@Setter
public class YsaParamException extends YsaServiceException {
    public YsaParamException(String desc) {
        super(YsaServiceExceptionEnum.PARAM_ERROR, desc);
    }
}
