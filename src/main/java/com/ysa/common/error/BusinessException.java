package com.ysa.common.error;

import com.ysa.common.enums.ResultCodeEnum;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/14 14:33
 * Modified By:
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = -1305325606818251614L;

    private int code = ResultCodeEnum.CODE_2.getCode();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BusinessException(String message) {
        super(message);
    }
}
