package littleprince.notification.command.application.service;

import littleprince.notification.command.domain.aggregate.NotificationType;
import littleprince.notification.command.domain.repository.NotificationTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationTypeService {

    private final NotificationTypeRepository repository;

    public String getTemplate(int type) {
        return repository.findByNotificationType(type)
                .map(NotificationType::getTemplate)
                .orElse("");
    }

    public String getFormattedTemplate(int type, Map<String, String> variables) {
        String template = getTemplate(type);
        log.info("🧩 원본 템플릿: {}", template);
        log.info("🧩 치환 변수: {}", variables);

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            template = template.replace("{" + entry.getKey() + "}", entry.getValue());
        }

        log.info("✅ 최종 결과: {}", template);
        return template;
    }
}

