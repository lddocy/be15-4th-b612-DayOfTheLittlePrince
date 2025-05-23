package littleprince.notification.command.domain.repository;

import littleprince.notification.command.domain.aggregate.WebPushSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebPushSubscriptionRepository extends JpaRepository<WebPushSubscription, Long> {
    Optional<WebPushSubscription> findByMemberId(Long memberId);
}

