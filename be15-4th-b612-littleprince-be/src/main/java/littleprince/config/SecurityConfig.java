package littleprince.config;

import littleprince.config.security.CustomAccessDeniedHandler;
import littleprince.config.security.CustomAuthenticationEntryPoint;
import littleprince.config.security.JwtAuthenticationFilter;
import littleprince.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                .exceptionHandling(exception
                        -> exception.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        "/v3/api-docs/**",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html")
                                .permitAll()
                                /* 일반 권한 */
                                /* TODO : 필요 할 때 주석 풀고 작성 해 주세요!*/
                                .requestMatchers(HttpMethod.POST,
                                        "/auth/login",
                                        "/member/signup",
                                        "/auth/reissue",
                                        "auth/change/password"
                                ).permitAll()

                                .requestMatchers(HttpMethod.GET,
                                        "/auth/find/password"
                                ).permitAll()
                                /* 유저 권한 */
                                .requestMatchers(HttpMethod.POST,
                                        "/**"
                                ).authenticated()
                                .requestMatchers(HttpMethod.GET,
                                        "/**"
                                ).authenticated()
                                .requestMatchers(HttpMethod.PUT,
                                        "/**"
                                ).authenticated()
                                .requestMatchers(HttpMethod.PATCH,
                                        "/**"
                                ).authenticated()
                                .requestMatchers(HttpMethod.DELETE,
                                        "/**"
                                ).authenticated()
                ).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        /* CORS 설정 */
        http
                .cors(cors -> cors
                        .configurationSource(corsConfigurationSource()));
        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*"); // 허용할 도메인
        config.addAllowedOrigin("http://localhost:5173"); // 허용할 도메인
        config.addAllowedOrigin("http://localhost:5174"); // 허용할 도메인
        config.addAllowedOrigin("http://localhost:5175"); // 허용할 도메인
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메소드 허용
        config.setAllowCredentials(true);// 자격 증명(쿠키 등) 허용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);// 모든 경로에 대해 설정
        return source;
    }
}