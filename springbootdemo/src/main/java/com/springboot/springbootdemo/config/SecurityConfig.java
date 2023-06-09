package com.springboot.springbootdemo.config;

import com.springboot.springbootdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    protected void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(userService);
    }

    protected void configure(HttpSecurity http) throws Exception{
        http.exceptionHandling().accessDeniedPage("403");
        http.authorizeRequests().antMatchers("/","/css/**","/js/**").permitAll();
        http.formLogin()
                .loginPage("/login").permitAll()//login,login.html
                .usernameParameter("user_email")//<input type="email" name="user_email">
                .passwordParameter("user_password")//<input type="password" name="user_password">
                .loginProcessingUrl("/auth").permitAll()//<form th:action ="@{'/auth'}" method = "post">
                .failureUrl("/login?error")
                .defaultSuccessUrl("/profile");
        http.logout()
                .logoutUrl("/logout").permitAll()//<form th:action ="@{'/logout'}" method = "post">
                .logoutSuccessUrl("/login");
//        http.csrf().disable();

    }

}
