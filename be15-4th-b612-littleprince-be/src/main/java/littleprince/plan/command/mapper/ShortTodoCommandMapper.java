package littleprince.plan.command.mapper;

import littleprince.plan.command.application.dto.request.CreateShortTodoRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import org.springframework.stereotype.Component;

@Component
public class ShortTodoCommandMapper {
    public Task toEntity(CreateShortTodoRequestDto dto, Long memberId) {
        return Task.builder()
                .memberId(memberId)
                .projectId(null)    // 단기 투두
                .content(dto.getContent())
                .date(dto.getDate())
                .build();
    }
}
