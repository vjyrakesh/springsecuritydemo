package com.rkasibhatla.securitydemo.configuration;

import com.rkasibhatla.securitydemo.repository.PersonRepository;
import com.rkasibhatla.securitydemo.service.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityDemoSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PersonRepository personRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new PersonDetailsService(personRepository));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/authhello")
                .authenticated()
                .antMatchers("/noauthhello", "/register")
                .permitAll()
                .and()
                .logout()
                .and()
                .httpBasic();

    }



    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
