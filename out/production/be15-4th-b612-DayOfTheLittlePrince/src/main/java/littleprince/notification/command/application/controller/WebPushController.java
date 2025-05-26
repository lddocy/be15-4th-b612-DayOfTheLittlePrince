package littleprince.notification.command.application.controller;

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

@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class WebPushController {

    private final WebPushSubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public ResponseEntity<Void> subscribe(@AuthenticationPrincipal CustomUserDetail user,
                                          @RequestBody WebPushSubscriptionRequest request) {
        subscriptionService.saveSubscription(user.getMemberId(), request);
        return ResponseEntity.ok().build();
    }
}

