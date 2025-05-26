package littleprince.notification.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.notification.command.application.service.WebPushSubscriptionService;
import littleprince.notification.command.domain.request.WebPushSubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "웹 푸시 알림")
@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class WebPushController {

    private final WebPushSubscriptionService subscriptionService;

    @Operation(summary = "알림 등록",description = "사용자에게 보여줄 알림을 등록한다.")
    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@AuthenticationPrincipal CustomUserDetail user,
                                          @RequestBody WebPushSubscriptionRequest request) {
        subscriptionService.saveSubscription(user.getMemberId(), request);
        return ResponseEntity.ok().build();
    }
}

