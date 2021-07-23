package com.ysa.common.error;


import lombok.Getter;
import lombok.Setter;

/**
 * @author ysa
 * @date 2020/6/30 10:51
 * @description 业务重复错误, 重复提交等
 */
@Getter
@Setter
public class YsaRepeatException extends YsaServiceException {
    public YsaRepeatException(String desc, Object data) {
        super(YsaServiceExceptionEnum.REPEAT_ERROR, desc, data);
    }
}
