package littleprince.item.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.item.query.dto.response.MyBadgeResponse;
import littleprince.item.query.service.BadgeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Operation(summary = "칭호 조회", description = "사용자는 자신이 획득한 칭호를 조회할 수 있다.")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MyBadgeResponse>> getMyBadges(@PathVariable Long userId) {
        MyBadgeResponse response = badgeQueryService.getObtainedBadges(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
