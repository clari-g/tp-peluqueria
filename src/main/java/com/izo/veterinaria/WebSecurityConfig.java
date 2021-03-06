package com.izo.veterinaria;

import com.izo.veterinaria.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/peluqueros/*").hasAuthority("ADMIN")
                .antMatchers("/peluqueros").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/perros/*").hasAuthority("ADMIN")
                .antMatchers("/perros").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/turnos/*").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/turnos").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/index.html").hasAnyAuthority("ADMIN","USER")
                .antMatchers("/*").rememberMe()
                .anyRequest().authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/index.html", true)
                .and().httpBasic()
                .and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}