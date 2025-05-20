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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                .exceptionHandling(exception
                        -> exception.authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .authorizeHttpRequests(auth ->
                        auth
                                /* 일반 권한 */
                                /* TODO : 필요 할 때 주석 풀고 작성 해 주세요!*/
//                                .requestMatchers(HttpMethod.GET,
//
//                                ).permitAll()
                                .requestMatchers(HttpMethod.POST,
                                        "/auth/login",
                                        "/member/signup"
                                ).permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        "/item/list"
                                ).authenticated()
                                /* 유저 권한 */
                                .requestMatchers(HttpMethod.POST,
                                        "/auth/logout",
                                        "/exp"

                                ).authenticated()
                                .requestMatchers(HttpMethod.GET,
                                        "/plan/short/{date}/todo",
                                        "/plan/long/{date}",
                                        "/plan/long/{date}/{projectId}",
                                        "/badges/{userId}"
                                ).authenticated()
                                .requestMatchers(HttpMethod.PATCH,
                                        "/badges/select"
                                ).authenticated()
                ).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }
}
