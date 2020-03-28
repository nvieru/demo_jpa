package com.example.demo.security;

<<<<<<< HEAD
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
>>>>>>> Basic in memory auth
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserDetailsService userDetailsService;

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
    auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
  }

  private PasswordEncoder getPasswordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return rawPassword.toString().equals(encodedPassword);
      }
    };
  }
=======

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * autorizare
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/book").permitAll()
                .antMatchers(HttpMethod.POST,"/book").permitAll()
                .antMatchers(HttpMethod.PUT,"/book/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/book/**").authenticated()
                .and().httpBasic();
    }

    /**
     * autentificare
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("ADMIN");
    }

>>>>>>> Basic in memory auth

}
