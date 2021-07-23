package com.ysa.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2021/7/23 18:31
 * Modified By:
 */
@Component
public class SpringContext implements ApplicationContextAware {


    private static ApplicationContext applicationContext;

    private SpringContext() {
        //私有构造器，防止被实例化...
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static Object getBeanByClassName(String className) {
        return applicationContext != null ? applicationContext.getBean(className) : null;
    }

    /**
     * 获取当前环境
     */
    public static String getActiveProfile() {
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
}
