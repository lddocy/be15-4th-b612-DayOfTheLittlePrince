package littleprince.notification.query.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private Long notificationId;     // 알림 ID
    private Long categoryId;         // 알림 유형 ID
    private String content;         // 알림 템플릿 (조인 결과)
    private String isRead;          // 읽음 여부
    private LocalDateTime createdAt; // 생성 시간
}
