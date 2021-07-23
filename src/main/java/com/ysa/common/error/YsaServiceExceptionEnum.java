package com.ysa.common.error;

/**
 * Created by ysa on 17/4/9.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务错误
 *
 * @author ysa
 */
@Getter
@AllArgsConstructor
public enum YsaServiceExceptionEnum {



    /**
     * 业务的参数错误
     */
    PARAM_ERROR(105),

    /**
     * 不可预知的错误，在整个拦截器最外层进行拦截
     */
    SERVICE_ERROR(106),

    /**
     * 不同的业务分支处理
     */
    CODE_107_ERROR(107),

    /**
     * 不同的业务分支处理
     */
    CODE_108_ERROR(108),

    /**
     * 不同的业务分支处理
     */
    CODE_109_ERROR(109),

    /**
     * 重复的错误，目前业务端在用，已知的情况是重复支付 返回
     */
    REPEAT_ERROR(110),
    
    /**
     * 不同的业务分支处理
     */
    CODE_111_ERROR(111),

    /**
     * 不同的业务分支处理
     */
    CODE_112_ERROR(112),

    /**
     * 不同的业务分支处理
     */
    CODE_113_ERROR(113),

    /**
     * 不同的业务分支处理
     */
    CODE_114_ERROR(114),

    /**
     * 不同的业务分支处理
     */
    CODE_115_ERROR(115),

    /**
     * 没有权限访问
     */
    NO_PERMISSION(10000),
    ;


    private int value;

}
