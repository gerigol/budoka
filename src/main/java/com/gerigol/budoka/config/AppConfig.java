package com.gerigol.budoka.config;


import com.gerigol.budoka.service.UserService;
import com.gerigol.budoka.utility.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@RequiredArgsConstructor
@Configuration
public class AppConfig {

    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> UserMapper.toAuthUser(userService.getUserByEmail(username));
    }
}
