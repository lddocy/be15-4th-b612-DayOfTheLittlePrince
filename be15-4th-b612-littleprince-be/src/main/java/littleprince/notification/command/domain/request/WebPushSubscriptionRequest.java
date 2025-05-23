package littleprince.notification.command.domain.request;

import lombok.Getter;

@Getter
public class WebPushSubscriptionRequest {
    private String endpoint;
    private String p256dh;
    private String auth;
}

