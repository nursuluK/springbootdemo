package com.springboot.springbootdemo.config;

import com.springboot.springbootdemo.beans.FirstBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public FirstBean firstBean(){
        return new FirstBean();
    }
}
