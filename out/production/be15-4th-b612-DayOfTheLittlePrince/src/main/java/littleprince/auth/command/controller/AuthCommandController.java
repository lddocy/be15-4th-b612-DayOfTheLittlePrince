package littleprince.auth.command.controller;

import jakarta.validation.Valid;
import littleprince.auth.command.dto.request.ChangePasswordRequest;
import littleprince.auth.command.service.AuthCommandService;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthCommandController {

    private final AuthCommandService authCommandService;

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ){
        if(refreshToken != null){
            authCommandService.logout(userDetail, refreshToken);
        }

        ResponseCookie deleteCookie =createDeleteRefreshTokenCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(ApiResponse.success(null));
    }

    @PostMapping("/change/password")
    public ResponseEntity<ApiResponse<Void>> changePassword(
            @RequestBody @Valid ChangePasswordRequest request
    ){
        authCommandService.changePassword(request);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 쿠키 삭제용*/
    private ResponseCookie createDeleteRefreshTokenCookie() {
        return ResponseCookie.from("refreshToken", "")
                .httpOnly(true)     // HttpOnly 유지
                // .secure(true)    // HTTPS 환경에서만 사용 시 주석 해제
                .path("/")          // 동일 path 범위
                .maxAge(0)          // 즉시 만료
                .sameSite("Strict") // SameSite 유지
                .build();
    }
}
