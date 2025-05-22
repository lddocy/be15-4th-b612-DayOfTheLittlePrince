package littleprince.notification.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class WebPushSubscription {
    @Id
    private Long memberId;

    private String endpoint;
    private String p256dh;
    private String auth;
}
