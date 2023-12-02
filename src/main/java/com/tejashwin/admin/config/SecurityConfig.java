package com.tejashwin.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

//    @Bean
//    public OpaqueTokenIntrospector opaqueTokenIntrospector() {
//        return new SpringOpaqueTokenIntrospector(
//                "http://localhost:8080/realms/tej-local-realm/protocol/openid-connect/token/introspect",
//                "tej-local-client-1",
//                "QtpKGsCJDIpyVmLi8QZz7kUdSim3YiSW");
//    }


}