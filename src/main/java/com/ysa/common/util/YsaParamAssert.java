package com.ysa.common.util;

import com.ysa.common.error.YsaParamException;

/**
 * @author ysa
 * @date 2020/7/14 12:00
 * @description yi23参数的断言 特有的断言,会抛出特定的异常信息
 */
public class YsaParamAssert {

    private YsaParamAssert() {
    }

    /**
     * 如果为空,会抛出参数错误的异常
     *
     * @param object  需要判断为空的对象
     * @param message 提示的错误信息
     * @see YsaParamException
     */
    public static void notEmpty(Object object, String message) {
        if (DataUtils.isEmpty(object)) {
            throw new YsaParamException(message);
        }
    }

    /**
     * 如果不为空,会抛出参数错误的异常
     *
     * @param object  需要判断为不空的对象
     * @param message 提示的错误信息
     * @see YsaParamException
     */
    public static void empty(Object object, String message) {
        if (DataUtils.isNotEmpty(object)) {
            throw new YsaParamException(message);
        }
    }

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new YsaParamException(message);
        }
    }
}
