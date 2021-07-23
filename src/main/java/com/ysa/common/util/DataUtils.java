package com.ysa.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author ysa
 * @date 2020/3/17 13:24
 * @description 对数据操作的工具类
 */
public final class DataUtils {

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj == "") {
            return true;
        }
        if (pObj instanceof String) {
            return ((String) pObj).trim().length() == 0;
        } else if (pObj instanceof Collection<?>) {
            return ((Collection<?>) pObj).size() == 0;
        } else if (pObj instanceof Map<?, ?>) {
            return ((Map<?, ?>) pObj).size() == 0;
        } else if (pObj instanceof Object[]) {
            return ((Object[]) pObj).length == 0;
        } else if (pObj instanceof boolean[]) {
            return ((boolean[]) pObj).length == 0;
        } else if (pObj instanceof byte[]) {
            return ((byte[]) pObj).length == 0;
        } else if (pObj instanceof char[]) {
            return ((char[]) pObj).length == 0;
        } else if (pObj instanceof short[]) {
            return ((short[]) pObj).length == 0;
        } else if (pObj instanceof int[]) {
            return ((int[]) pObj).length == 0;
        } else if (pObj instanceof long[]) {
            return ((long[]) pObj).length == 0;
        } else if (pObj instanceof float[]) {
            return ((float[]) pObj).length == 0;
        } else if (pObj instanceof double[]) {
            return ((double[]) pObj).length == 0;
        }
        return false;
    }

    /**
     * 判断对象是否为NotEmpty(!null或元素>0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isNotEmpty(Object pObj) {
        return !isEmpty(pObj);
    }

    /**
     * 得到默认值
     *
     * @param value
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T getDefaultValue(T value, T defaultValue) {
        return getDefaultValue(value, defaultValue, a -> a);
    }

    /**
     * 得到默认值, 如果原值不为空可以处理
     *
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T, S> T getDefaultValue(S value, T defaultValue, Function<S, T> function) {
        return isNotEmpty(value)
                ? function.apply(value)
                : defaultValue;
    }

    /**
     * 得到默认值, 如果原值不为空可以处理
     *
     * @param value
     * @param defaultValue
     * @param function
     * @param <T>
     * @return
     */
    public static <T, S> T getDefaultValueSafety(S value, T defaultValue, Function<S, T> function) {
        try {
            return isNotEmpty(value)
                    ? function.apply(value)
                    : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }


    /**
     * 将json转换为 map
     *
     * @param objStr
     * @return
     */
    public static Map<String, String> json2Map(String objStr) {
        Map<String, String> data = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(objStr);
        for (Object key : jsonObject.keySet()) {
            String value = jsonObject.getString(key.toString());
            if (isNotEmpty(value)) {
                data.put(key.toString(), value);
            }
        }
        return data;
    }

    /**
     * 得到值, 如果原值不为空可以处理
     *
     * @param dataGetter   获取值
     * @param defaultValue 默认值
     * @param <T>          类型
     * @return 获取值 保证不会出现空指针
     */
    public static <T> T getValueSafety(Supplier<T> dataGetter, T defaultValue) {
        try {
            return dataGetter.get();
        } catch (Exception e) {
            return defaultValue;
        }
    }

}