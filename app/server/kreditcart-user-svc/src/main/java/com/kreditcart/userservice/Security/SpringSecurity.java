package com.kreditcart.userservice.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;

@Configuration
public class SpringSecurity {
    @Bean // Instruct SpringBoot to consider this object, instead of creating it's own
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests(authorize ->authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }
    @Bean // Purpose is it return singleton Object,
          // I want to use the same secret for validation using which token was created
    public SecretKey secret() {
        MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
        return macAlgorithm.key().build();
    }
}
