package littleprince.notification.command.domain.aggregate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class NotificationType {

    @Id
    private int notificationType;

    private String template;
}
