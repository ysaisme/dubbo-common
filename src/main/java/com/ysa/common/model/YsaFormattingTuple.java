package com.ysa.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ysa
 * @date 2020/11/7 12:55 PM
 */
@AllArgsConstructor
@Data
public class YsaFormattingTuple {

    /**
     * 消息体
     */
    private String message;

    /**
     * 消息拼接的参数
     */
    private Object[] argArray;

    /**
     * 异常
     */
    private Throwable throwable;


    public YsaFormattingTuple(String message) {
        this(message, null, null);
    }
}
