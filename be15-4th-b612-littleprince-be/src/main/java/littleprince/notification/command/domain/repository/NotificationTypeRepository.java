package littleprince.notification.command.domain.repository;

import littleprince.notification.command.domain.aggregate.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Integer> {
    Optional<NotificationType> findByNotificationType(int notificationType);
}

