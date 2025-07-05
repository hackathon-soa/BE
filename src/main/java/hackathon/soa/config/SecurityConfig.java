package hackathon.soa.config;

import hackathon.soa.common.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/**").permitAll()  // 로그인, 회원가입 허용
                        .requestMatchers("/api/home/**", "/api/stories").permitAll()  // 홈화면 허용
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()  // Swagger 허용
                        .requestMatchers("/courses/**").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/api/courses/**").permitAll()
                        .requestMatchers("/api/stories/image").permitAll()
                        .requestMatchers("/api/participation/segments/*/approve").permitAll()
                        .requestMatchers("/api/participation/segments/*/reject").permitAll()
                        .anyRequest().authenticated()  // 그 외 모든 요청은 인증 필요
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
}