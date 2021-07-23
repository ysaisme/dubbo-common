package com.ysa.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/12 11:05
 * Modified By:
 */
@Getter
@ToString
@AllArgsConstructor
public enum ResultCodeEnum implements Serializable {


    SUCCEED(0, "OK", true),
    CODE_1(1, "你没有访问权限！", false),
    CODE_2(2, "{0}", false),
    CODE_4(4, "会话超时，请重新登录！", false),
    CODE_3(3, "系统异常", false),
    CODE_40(40, "无token", false);

    private int code;
    private String message;
    private Boolean success;

    public static ResultCodeEnum getByCode(int code) {
        ResultCodeEnum[] enums = ResultCodeEnum.values();
        for (ResultCodeEnum item : enums) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return ResultCodeEnum.CODE_3;
    }
}
