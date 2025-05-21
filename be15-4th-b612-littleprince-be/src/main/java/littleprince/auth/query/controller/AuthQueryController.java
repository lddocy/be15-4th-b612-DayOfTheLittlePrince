package littleprince.auth.query.controller;

import jakarta.validation.Valid;
import littleprince.auth.command.dto.response.TokenResponse;
import littleprince.auth.query.dto.request.LoginRequest;
import littleprince.auth.query.service.AuthQueryService;
import littleprince.common.dto.ApiResponse;
import littleprince.member.annotation.ValidEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthQueryController {
    private final AuthQueryService authQueryService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> login(
            @RequestBody @Valid LoginRequest request
    ){
        TokenResponse response = authQueryService.login(request);

        return buildTokenResponse(response);
    }

    @PostMapping("/reissue")
    public ResponseEntity<ApiResponse<TokenResponse>> reissue(
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ){
        TokenResponse tokenResponse = authQueryService.reissue(refreshToken);

        return buildTokenResponse(tokenResponse);
    }

    @GetMapping("/find/password")
    public ResponseEntity<ApiResponse<Void>> findPassword(
            @RequestParam @ValidEmail String email
    ){
        authQueryService.findPassword(email);

        return ResponseEntity.ok(ApiResponse.success(null));
    }


    /* accessToken -> body
     * refreshToken -> cookie*/
    private ResponseEntity<ApiResponse<TokenResponse>> buildTokenResponse(TokenResponse tokenResponse){
        ResponseCookie cookie = createRefreshTokenCookie(tokenResponse.getRefreshToken());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(ApiResponse.success(tokenResponse));
    }

    private ResponseCookie createRefreshTokenCookie(String refreshToken) {
        return ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)                     // HttpOnly 속성 설정 (JavaScript 에서 접근 불가)
                // .secure(true)                    // HTTPS 환경일 때만 전송 (운영 환경에서 활성화 권장)
                .path("/")                          // 쿠키 범위 : 전체 경로
                .maxAge(Duration.ofDays(7))         // 쿠키 만료 기간 : 7일
                .sameSite("Strict")                 // CSRF 공격 방어를 위한 SameSite 설정
                .build();
    }
}
