package littleprince.item.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.item.query.dto.response.MyBadgeResponse;
import littleprince.item.query.service.BadgeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "칭호")
@RestController
@RequiredArgsConstructor
@RequestMapping("/badges")
public class BadgeQueryController {

    private final BadgeQueryService badgeQueryService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<MyBadgeResponse>> getMyBadges(@AuthenticationPrincipal CustomUserDetail user) {
        Long memberId = user.getMemberId();
        MyBadgeResponse response = badgeQueryService.getObtainedBadges(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @GetMapping("/selected")
    public ResponseEntity<ApiResponse<String>> getSelectedBadge(
            @AuthenticationPrincipal CustomUserDetail user
    ) {
        Long memberId = user.getMemberId();
        String selectedBadge = badgeQueryService.getSelectedBadge(memberId);
        return ResponseEntity.ok(ApiResponse.success(selectedBadge));
    }
}
