/*
package com.cn.camunda.security.config.jwt;

import com.cn.camunda.security.config.jwt.groovy.ProcessEngineAuthenticationFilterJwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.servlet.Filter;

@Configuration
//@Order(SecurityProperties.BASIC_AUTH_ORDER - 20)
public class JwtRestSecurityConfig  { //extends WebSecurityConfigurerAdapter
    @Value("${camunda.rest-api.jwt.secret-path}")
    String jwtSecretPath;

    @Value("${camunda.rest-api.jwt.validator-class}")
    String jwtValidatorClass;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/engine-rest/**")
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .httpBasic(); // this is just an example, use any auth mechanism you like
//
//    }

    @Bean
    public FilterRegistrationBean processEngineAuthenticationFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("camunda-jwt-auth");
        registration.addInitParameter("authentication-provider", "com.cn.camunda.security.config.jwt.groovy.AuthenticationFilterJwt");
        registration.addInitParameter("jwt-secret-path", jwtSecretPath);
        registration.addInitParameter("jwt-validator", jwtValidatorClass);
        registration.setFilter(getProcessEngineAuthenticationFilter());
//        registration.setOrder(102);
        registration.addUrlPatterns("/engine-rest/*", "/rest/*");
        return registration;
    }

    @Bean
    public Filter getProcessEngineAuthenticationFilter() {
        return new ProcessEngineAuthenticationFilterJwt();
    }
}
*/
