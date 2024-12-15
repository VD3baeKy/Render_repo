package com.example.samuraitravel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration //バージョンが上がったことで必須となっている。
@EnableWebSecurity
//@EnableMethodSecurity  //エラーになるのでコメントアウト。
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        String passEnv = System.getenv("ACCESS_PASS");
        //.password(passwordEncoder().encode("bmrkepmvd"))
        UserDetails user = User
                .withUsername("user")
                .password(passwordEncoder().encode(passEnv))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
