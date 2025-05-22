package littleprince.notification.command.domain.aggregate;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long categoryId;
    private Long memberId;

    private LocalDateTime createdAt;

    private Boolean isRead = false;
}
