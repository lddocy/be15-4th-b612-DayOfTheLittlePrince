package littleprince.notification.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationResponse {
    private String template;
    private LocalDateTime createdAt;
}

