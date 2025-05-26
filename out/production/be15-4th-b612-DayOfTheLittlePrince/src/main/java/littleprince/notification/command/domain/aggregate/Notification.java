package littleprince.notification.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    private Long categoryId;
    private Long memberId;

    private LocalDateTime createdAt;

    @Column(name = "is_read")
    private String isRead = "N";

    @Column(nullable = false)
    private String content;


}
