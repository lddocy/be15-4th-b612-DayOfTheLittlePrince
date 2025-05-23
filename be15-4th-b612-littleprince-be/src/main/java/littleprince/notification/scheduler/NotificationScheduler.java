package littleprince.notification.scheduler;

import littleprince.notification.command.application.service.NotificationTypeService;
import littleprince.notification.command.application.service.PushNotificationService;
import littleprince.notification.command.application.service.WebPushSubscriptionService;
import littleprince.notification.command.domain.aggregate.WebPushSubscription;
import littleprince.plan.query.service.ShortListQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final WebPushSubscriptionService subscriptionService;
    private final ShortListQueryService shortListQueryService;
    private final PushNotificationService pushNotificationService;
    private final NotificationTypeService templateService; // ⬅️ 누락 주입 추가

    // ⏰ 매 분 0초에 실행 (테스트용)
    @Scheduled(cron = "0 10 * * * *")
    public void sendMorningNotification() {
        String template = templateService.getTemplate(2); // 예: 좋은 아침이에요!
        for (WebPushSubscription sub : subscriptionService.findAll()) {
            pushNotificationService.sendPush(sub, template, 2L);
        }
    }

    @Scheduled(cron = "0 10 * * * *")
    public void sendEveningNotification() {
        for (WebPushSubscription sub : subscriptionService.findAll()) {
            long count = shortListQueryService.countUncheckedTasks(sub.getMemberId(), Date.valueOf(LocalDate.now()));
            String message = templateService.getFormattedTemplate(3, Map.of("count", String.valueOf(count)));
            pushNotificationService.sendPush(sub, message, 3L); // 치환된 message 직접 전달
        }
    }

}
