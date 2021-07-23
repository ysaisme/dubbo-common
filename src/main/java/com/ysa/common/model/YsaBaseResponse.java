package com.ysa.common.model;

import com.ysa.common.error.YsaServiceExceptionEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ysa
 * @date 2020/3/28 13:49
 * @description
 */
@Data
public class YsaBaseResponse implements Serializable {
    /**
     * 返回的code值
     *
     * @see YsaServiceExceptionEnum
     */
    private int code;
    /**
     * 返回的消息体
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;
}
