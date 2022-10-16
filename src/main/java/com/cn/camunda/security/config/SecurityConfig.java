package com.cn.camunda.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    //    org.springframework.security.core.userdetails.User [Username=demo, Password=[PROTECTED], Enabled=true,
//    AccountNonExpired=true, credentialsNonExpired=true, AccountNonLocked=true, Granted Authorities=[ROLE_ACTUATOR, ROLE_camunda-admin]]
    // This is just a very simple Identity Management solution for demo purposes.
    // In real world scenarios, this would be replaced by the actual IAM solution
    @SuppressWarnings("deprecation")
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder().username("demo").password("demo").roles("ACTUATOR", "camunda-admin").build());
        manager.createUser(User.withDefaultPasswordEncoder().username("john").password("john").roles("camunda-user").build());
        return manager;
    }

}