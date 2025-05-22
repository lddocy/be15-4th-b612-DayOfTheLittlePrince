package littleprince.notification.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class NotificationListResponse {
    private List<NotificationResponse> notifications; // ✅ 여기
    private long unreadCount;
    private long totalCount;
}
