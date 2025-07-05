package hackathon.soa.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                                // TODO 임시적으로 모든 경로 허용
                                .requestMatchers("/**").permitAll()
//                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger 인증 없이 허용
//                            .requestMatchers("/auth/**").permitAll() // 회원가입 & 로그인 인증 없이 허용
//                            .anyRequest().authenticated() // 그 외의 경로는 인증 요구
                )
                .csrf(csrf -> csrf.disable()); // CSRF 보호 비활성화

        return http.build();
    }
}