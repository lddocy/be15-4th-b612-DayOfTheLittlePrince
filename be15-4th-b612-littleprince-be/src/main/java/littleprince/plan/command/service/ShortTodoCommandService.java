package littleprince.plan.command.service;

import littleprince.plan.command.application.dto.request.CreateShortTodoRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.mapper.ShortTodoCommandMapper;
import littleprince.plan.command.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ShortTodoCommandService {
    private final TaskRepository taskRepository;
    private final ShortTodoCommandMapper mapper;

    @Transactional
    public void create(Long memberId, CreateShortTodoRequestDto dto) {
        Task task = mapper.toEntity(dto, memberId);
        taskRepository.save(task);
    }
}
