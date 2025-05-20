package littleprince.item.command.application.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.item.command.application.dto.request.BadgeSelectRequest;
import littleprince.item.command.application.service.BadgeCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/badges")
@RequiredArgsConstructor
public class BadgeCommandController {

    private final BadgeCommandService badgeCommandService;

    @PatchMapping("/select")
    public ResponseEntity<ApiResponse<Void>> selectBadge(@AuthenticationPrincipal CustomUserDetail user,
                                                         @RequestBody BadgeSelectRequest request) {
        Long memberId = user.getMemberId();
        badgeCommandService.selectBadge(memberId, request.getBadgeId());
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
