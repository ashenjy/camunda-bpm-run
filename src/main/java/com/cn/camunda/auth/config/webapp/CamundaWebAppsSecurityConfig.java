package com.cn.camunda.auth.config.webapp;

import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.DispatcherType;
import java.util.Collections;
import java.util.EnumSet;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER - 20)
public class CamundaWebAppsSecurityConfig extends WebSecurityConfigurerAdapter {

    // The paths used by camunda webapps. These are the paths that our HttpSecurity applies to
    private static final String[] CAMUNDA_APP_PATHS = {"/camunda/app/**", "/camunda/api/**", "/camunda/lib/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Only apply this HttpSecurity to the camunda webapp paths
                .requestMatchers().antMatchers(CAMUNDA_APP_PATHS).and()
                // Disable CSRF for these paths
                .csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
//        http.antMatcher(CAMUNDA_APP_PATHS).authorizeRequests().anyRequest().authenticated().and().httpBasic();// this is just an example, use any auth mechanism you like
    }

    @Bean
    public FilterRegistrationBean<ContainerBasedAuthenticationFilter> containerBasedAuthenticationFilter() {

        FilterRegistrationBean<ContainerBasedAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(new ContainerBasedAuthenticationFilter());
        registrationBean.setInitParameters(Collections.singletonMap("authentication-provider", "com.cn.camunda.auth.config.webapp.filter.SpringSecurityAuthenticationProvider"));
//        registrationBean.setOrder(102); // make sure the filter is registered after the Spring Security Filter Chain
        registrationBean.addUrlPatterns("/camunda/app/*"); //, "/camunda/api/*"
        registrationBean.setDispatcherTypes(EnumSet.of(DispatcherType.REQUEST));
        return registrationBean;
    }
}