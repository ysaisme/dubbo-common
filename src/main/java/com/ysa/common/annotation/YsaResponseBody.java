package com.ysa.common.annotation;

import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.annotation.*;

/**
 * @author ysa
 * @date 2020/3/28 14:26
 * @description 会在拦截器那里判断是否有这个注解，如果存在 会包装成 {code:'',data:''} 的形式
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YsaResponseBody {

    /**
     * fastjson 序列化可选
     *
     * @return
     * @see SerializerFeature
     */
    SerializerFeature[] serializerFeature() default {};
}
