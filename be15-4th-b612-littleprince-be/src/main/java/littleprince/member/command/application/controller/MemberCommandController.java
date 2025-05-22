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
    ){
        memberCommandService.signup(request);
        return ResponseEntity.ok(ApiResponse.success(null));
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

}
