package littleprince.member.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import littleprince.common.dto.ApiResponse;
import littleprince.member.command.application.dto.request.SignupRequest;
import littleprince.member.command.application.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
