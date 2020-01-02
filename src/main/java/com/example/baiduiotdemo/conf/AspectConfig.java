package com.example.baiduiotdemo.conf;

import com.example.baiduiotdemo.aspect.TokenCheck;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public TokenCheck getTokenCheck() {
        return new TokenCheck();
    }
}
