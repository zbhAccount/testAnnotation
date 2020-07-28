package com.annotation.study.user.configuration;

import com.annotation.study.user.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationFactory {
    @Bean("userByConfiguretion")
    public User getUser() {
        return new User(null, "bohan.zhou", 23, 1);
    }
}
