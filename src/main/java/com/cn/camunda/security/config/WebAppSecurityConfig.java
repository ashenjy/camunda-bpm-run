package com.cn.camunda.security.config;

import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.EnumSet;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] CAMUNDA_APP_PATHS = {"/camunda/app/**", "/camunda/api/**", "/camunda/lib/**"};
    private static final String[] CAMUNDA_UI_URL_PATTERNS = {"/camunda/app/*"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/camunda/app/**").authorizeRequests().anyRequest().authenticated().and().httpBasic();// this is just an example, use any auth mechanism you like
    }

    @Bean
    public FilterRegistrationBean<ContainerBasedAuthenticationFilter> containerBasedAuthenticationFilter() {

        FilterRegistrationBean<ContainerBasedAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(new ContainerBasedAuthenticationFilter());
        registrationBean.setInitParameters(Collections.singletonMap("authentication-provider", "com.cn.camunda.security.filter.webapp.SpringSecurityAuthenticationProvider"));
        registrationBean.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
        registrationBean.addUrlPatterns(CAMUNDA_UI_URL_PATTERNS);
        registrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
        return registrationBean;
    }
}