package com.scm.config;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.services.Impl.SecurityCustomUserDetailServiceImpl;

@Configuration
public class SecurityConfig {

    @Autowired
    private SecurityCustomUserDetailServiceImpl userDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // config
        httpSecurity.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
            // authorize.requestMatchers("/home", "/signup", "/do-register").permitAll();

        });

        // setting up using the singup form. where the user
        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login").loginProcessingUrl("/authenticate");
            formLogin.defaultSuccessUrl("/user/dashboard");
            // formLogin.failureForwardUrl("/login?error=true");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            // formLogin.failureHandler(authFailtureHandler);
        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm -> {
            logoutForm.logoutUrl("/logout");
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });

        // oauth Configuration
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            // oauth.successHandler(null);
        });

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Encrypt the password
        return new BCryptPasswordEncoder();
    }

}
