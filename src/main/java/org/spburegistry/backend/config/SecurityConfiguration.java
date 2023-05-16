package org.spburegistry.backend.config;

import org.spburegistry.backend.utils.PropertiesParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@PropertySource("classpath:application.properties")
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final PropertiesParser propertiesParser;

    @Autowired
    public SecurityConfiguration(JwtFilter jwtFilter, PropertiesParser propertiesParser) {
        this.jwtFilter = jwtFilter;
        this.propertiesParser = propertiesParser;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] permittedPaths = propertiesParser.getPermittedPaths()
                .stream()
                .map(path -> path + "/**")
                .toList()
                .toArray(new String[0]);
        return http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> response.setStatus(HttpStatus.UNAUTHORIZED.value())
                )
                .and()
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(permittedPaths).permitAll()
                                .anyRequest().authenticated()
                                .and()
                                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                ).build();

    }
}