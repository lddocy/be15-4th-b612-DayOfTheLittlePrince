package littleprince.notification.command.domain.repository;

import littleprince.notification.command.domain.aggregate.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
