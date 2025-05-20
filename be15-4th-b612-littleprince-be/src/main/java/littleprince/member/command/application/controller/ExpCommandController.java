package littleprince.member.command.application.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.member.command.application.dto.request.ExpRequest;
import littleprince.member.command.application.dto.response.ExpResponse;
import littleprince.member.command.application.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exp")
@RequiredArgsConstructor
public class ExpCommandController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public ResponseEntity<ApiResponse<ExpResponse>> addExp(@AuthenticationPrincipal CustomUserDetail user,
                                                           @RequestBody ExpRequest request) {
        Long memberId = user.getMemberId();
        int amount = request.getExpPoint();
        ExpResponse response = memberCommandService.addExp(memberId, amount);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
