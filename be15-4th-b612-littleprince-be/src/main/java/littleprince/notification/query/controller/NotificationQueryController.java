package littleprince.notification.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "알림")
@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    @Operation(summary = "알림 조회",description = "사용자는 알림 조회할 수 있다.")
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
