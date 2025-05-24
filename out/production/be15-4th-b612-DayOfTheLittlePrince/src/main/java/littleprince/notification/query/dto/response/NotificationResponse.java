package littleprince.notification.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private Long notificationId;
    private String content;
    private LocalDateTime createdAt;
    private String isRead;
    private Long categoryId;
}

