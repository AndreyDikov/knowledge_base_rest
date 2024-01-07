package ru.knowledge_base_rest.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация для Spring Security
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final ApplicationContext applicationContext;

    @Autowired
    public SecurityConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Фильтры для запросов.
     * Тут указывается к чему и с какой ролью имеет доступ пользователь
     * @param http
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/registration", "/login", "/")
                .permitAll()
//                .requestMatchers("/users/**", "/works/**")
//                .hasAnyAuthority("ROLE_BASIC_STATE")
//                .requestMatchers("/userToWorks/**")
//                .hasAnyAuthority("ROLE_ADMINISTRATION_STATE")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
        return http.build();
    }

    /**
     * Тут указывается тип шифрования пароля пользователей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // todo зашифровать
        return NoOpPasswordEncoder.getInstance();
    }
}