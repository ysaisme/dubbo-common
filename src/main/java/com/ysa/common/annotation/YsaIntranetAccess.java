package com.ysa.common.annotation;

import java.lang.annotation.*;

/**
 * @author ysa
 * @date 2020/5/7 17:41
 * @description 有些http接口只能允许内网访问, 拒绝公网访问
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YsaIntranetAccess {
}
