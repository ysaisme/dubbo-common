package com.ysa.common.config;

import com.ysa.common.aspect.RPCResponseAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ysa
 * @date 2020/9/3 15:39
 * @description
 */
@Configuration
public class CommonBaseAutoConfiguration {
    @Bean
    public RPCResponseAspect getRPCResponseAspect() {
        return new RPCResponseAspect();
    }
}
