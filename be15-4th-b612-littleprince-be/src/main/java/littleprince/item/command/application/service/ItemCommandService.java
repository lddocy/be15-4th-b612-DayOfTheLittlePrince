package littleprince.item.command.application.service;

import littleprince.item.command.domain.repository.JpaItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCommandService {
    private final JpaItemRepository jpaItemRepository;
}
