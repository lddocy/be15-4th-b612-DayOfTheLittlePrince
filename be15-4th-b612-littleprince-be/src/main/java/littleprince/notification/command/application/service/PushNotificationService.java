package littleprince.notification.command.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import littleprince.notification.command.domain.aggregate.Notification;
import littleprince.notification.command.domain.aggregate.WebPushSubscription;
import littleprince.notification.command.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.PushService;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PushNotificationService {

    private final NotificationRepository notificationRepository;

    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    @Value("${webpush.vapid.public-key}")
    private String publicKey;

    @Value("${webpush.vapid.private-key}")
    private String privateKey;

    @Value("${webpush.vapid.subject}")
    private String subject;

    public void sendPush(WebPushSubscription subscription, String message, Long categoryId) {
        try {
            // ✅ 1. DB에 알림 저장
            Notification notification = Notification.builder()
                    .memberId(subscription.getMemberId())
                    .categoryId(categoryId)
                    .createdAt(LocalDateTime.now())
                    .isRead("N")
                    .content(message)
                    .build();
            notificationRepository.save(notification);

            // ✅ 2. 푸시 알림 전송
            nl.martijndwars.webpush.Notification webPushNotification = new nl.martijndwars.webpush.Notification(
                    subscription.getEndpoint(),
                    subscription.getP256dh(),
                    subscription.getAuth(),
                    new ObjectMapper().writeValueAsString(Map.of("body", message))
            );

            PushService pushService = new PushService()
                    .setPublicKey(publicKey)
                    .setPrivateKey(privateKey)
                    .setSubject(subject);

            pushService.send(webPushNotification);

        } catch (Exception e) {
            log.error("푸시 알림 전송 실패: {}", e.getMessage());
        }
    }
}
