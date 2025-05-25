package littleprince.notification.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.notification.command.application.service.NotificationCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "알림")
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;

    @Operation(summary="알림 확인", description = "사용자는 알림 읽음표시를 할 수 있다.")
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(
            @PathVariable Long notificationId,
            @AuthenticationPrincipal CustomUserDetail user
    ) {
        notificationCommandService.markAsRead(user.getMemberId(), notificationId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
