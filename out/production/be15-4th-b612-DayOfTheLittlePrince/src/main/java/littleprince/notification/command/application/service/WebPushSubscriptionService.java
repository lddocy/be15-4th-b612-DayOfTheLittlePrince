package littleprince.notification.command.application.service;

import littleprince.notification.command.domain.aggregate.WebPushSubscription;
import littleprince.notification.command.domain.repository.WebPushSubscriptionRepository;
import littleprince.notification.command.domain.request.WebPushSubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebPushSubscriptionService {

    private final WebPushSubscriptionRepository repository;

    public void saveSubscription(Long memberId, WebPushSubscriptionRequest request) {
        WebPushSubscription subscription = new WebPushSubscription();
        subscription.setMemberId(memberId);
        subscription.setEndpoint(request.getEndpoint());
        subscription.setP256dh(request.getP256dh());
        subscription.setAuth(request.getAuth());

        repository.save(subscription);
    }

    public List<WebPushSubscription> findAll() {
        return repository.findAll();
    }

    public Optional<WebPushSubscription> findByMemberId(Long memberId) {
        return repository.findByMemberId(memberId);
    }
}

