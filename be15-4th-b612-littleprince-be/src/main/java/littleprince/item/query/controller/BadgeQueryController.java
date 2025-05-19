package littleprince.item.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.item.query.dto.response.MyBadgeResponse;
import littleprince.item.query.service.BadgeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/badges")
public class BadgeQueryController {

    private final BadgeQueryService badgeQueryService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<MyBadgeResponse>> getMyBadges(@PathVariable Long userId) {
        MyBadgeResponse response = badgeQueryService.getObtainedBadges(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
