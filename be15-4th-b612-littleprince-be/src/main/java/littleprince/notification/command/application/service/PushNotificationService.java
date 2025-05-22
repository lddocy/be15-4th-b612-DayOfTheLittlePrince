//package littleprince.notification.command.application.service;
//
//import littleprince.notification.command.domain.aggregate.WebPushSubscription;
//import lombok.RequiredArgsConstructor;
//import nl.martijndwars.webpush.PushService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class PushNotificationService {
//
//    @Value("${webpush.vapid.public-key}")
//    private String publicKey;
//
//    @Value("${webpush.vapid.private-key}")
//    private String privateKey;
//
//    @Value("${webpush.vapid.subject}")
//    private String subject;
//
//    public void sendPush(WebPushSubscription subscription, String payload) {
//        try {
//            PushService pushService = new PushService()
//                    .setPublicKey(publicKey)
//                    .setPrivateKey(privateKey)
//                    .setSubject(subject);
//
//            Notification notification = new Notification(
//                    subscription.getEndpoint(),
//                    subscription.getP256dh(),
//                    subscription.getAuth(),
//                    payload
//            );
//
//            pushService.send(notification);
//        } catch (Exception e) {
//            log.error("푸시 알림 전송 실패: {}", e.getMessage());
//        }
//    }
//}
//
