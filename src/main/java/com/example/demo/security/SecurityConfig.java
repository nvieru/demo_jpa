package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/book").permitAll()
            .antMatchers(HttpMethod.POST, "/book").permitAll()
            .antMatchers(HttpMethod.PUT, "/book/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/book/**").authenticated()
            .and().httpBasic();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
  }

}
