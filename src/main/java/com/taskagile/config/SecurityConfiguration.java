package com.taskagile.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC = new String[]{
        "/error", "/login", "/logout", "/register", "/api/registrations"};
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(PUBLIC).permitAll()
            .anyRequest().authenticated()
            .anyRequest().authenticated()
            .and()
            .formLogin() {}
    }
}
