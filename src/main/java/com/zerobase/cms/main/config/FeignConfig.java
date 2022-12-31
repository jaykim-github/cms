package com.zerobase.cms.main.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Qualifier(value = "mailgun")
    @Bean
    public BasicAuthRequestInterceptor baseAuthRequestInterceptor (){
        return new BasicAuthRequestInterceptor("api", "92d4e17696f14585b7b1b27737510b21-c2efc90c-a8a97bc6");
    }
}
