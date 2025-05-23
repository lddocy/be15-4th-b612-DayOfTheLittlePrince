package littleprince.notification.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.notification.query.dto.response.NotificationListResponse;
import littleprince.notification.query.service.NotificationQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<NotificationListResponse>> getMyNotifications(
            @AuthenticationPrincipal CustomUserDetail user,
            @RequestParam(defaultValue = "30") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        Long memberId = user.getMemberId();

        NotificationListResponse response =
                notificationQueryService.getByMemberId(memberId, offset, limit); // 순서 주의!

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
