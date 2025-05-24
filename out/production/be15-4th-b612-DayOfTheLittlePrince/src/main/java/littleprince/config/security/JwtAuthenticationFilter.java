package littleprince.config.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.utils.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = parseToken(request);

        if(token != null && jwtTokenProvider.validateToken(token)){
            Long memberId = jwtTokenProvider.getMemberIdFromJWT(token);
            String email = jwtTokenProvider.getEmailFromJWT(token);

            CustomUserDetail userDetail = CustomUserDetail.builder()
                    .memberId(memberId)
                    .email(email)
                    .build();
            PreAuthenticatedAuthenticationToken authentication
                    = new PreAuthenticatedAuthenticationToken(
                    userDetail, null,userDetail.getAuthorities()
            );
            log.info("[Jwt Filter] 인증된 유저: memberId={}, email={}", memberId, email);


            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String parseToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
