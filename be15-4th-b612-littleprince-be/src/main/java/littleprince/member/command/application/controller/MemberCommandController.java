package littleprince.member.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.member.command.application.dto.request.PlanetNameRequest;
import littleprince.member.command.application.dto.request.SignupRequest;
import littleprince.member.command.application.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원")
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberCommandController {
    private final MemberCommandService memberCommandService;

    @Operation(summary = "회원 가입", description = "이메일과 비밀번호로 회원으로 가입할 수 있다.")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<Void>> signup(
            @RequestBody @Valid SignupRequest request
    ) {
        memberCommandService.signup(request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "회원 탈퇴", description = "사용자는 회원에서 탈퇴할 수 있다.")
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Void>> delete(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        if (refreshToken != null) {
            memberCommandService.delete(userDetail, refreshToken);
        }
        ResponseCookie deleteCookie = createDeleteRefreshTokenCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, deleteCookie.toString())
                .body(ApiResponse.success(null));
    }

    @Operation(summary = "행성 이름 수정", description = "사용자는 행성 이름을 수정할 수 있다.")
    @PatchMapping("/planet/edit")
    public ResponseEntity<ApiResponse<Void>> updatePlanetName(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody PlanetNameRequest request
    ) {
        Long memberId = userDetail.getMemberId();
        memberCommandService.changePlanetName(memberId, request);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /* 쿠키 삭제용*/
    private ResponseCookie createDeleteRefreshTokenCookie() {
        return ResponseCookie.from("refreshToken", "")
                .httpOnly(true)                     // HttpOnly 유지
                // .secure(true)                    // HTTPS 환경에서만 사용 시 주석 해제
                .path("/")                          // 동일 path 범위
                .maxAge(0)             // 즉시 만료
                .sameSite("Strict")                 // SameSite 유지
                .build();
    }
}