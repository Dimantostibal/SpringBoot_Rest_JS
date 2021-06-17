package com.example.SpringBootTest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final LoginSuccessHandler loginSuccessHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/login").anonymous()
                    .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/users/**").hasRole("ADMIN")
                .and()
                    .formLogin()
                    // указываем страницу с формой логина
                    .loginPage("/login")
                    //указываем логику обработки при логине
                    .successHandler(loginSuccessHandler)
                    // указываем action с формы логина
                    .loginProcessingUrl("/login")
                    // Указываем параметры логина и пароля с формы логина
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    // даем доступ к форме логина всем
                    .permitAll()
                .and()
                    .logout()
                    // разрешаем делать логаут всем
                    .permitAll()
                    // указываем URL логаута
                    .logoutUrl("/logout")
                    // указываем URL при удачном логауте
                    .logoutSuccessUrl("/login?logout");
        // Необходимо для шифрования паролей
        // В данном примере не используется, отключен
    }
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}