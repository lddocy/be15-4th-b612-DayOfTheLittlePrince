package littleprince.plan.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.plan.command.application.dto.request.CreateShortTodoRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.mapper.ShortTodoCommandMapper;
import littleprince.plan.command.repository.TaskRepository;
import littleprince.plan.exception.PlanErrorCode;
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

    @Transactional
    public void deleteShortTodo(Long memberId, Long taskId) {
        Task task = taskRepository.findByTaskIdAndMemberId(taskId, memberId)
                .orElseThrow(() -> new BusinessException(PlanErrorCode.ACCESS_DENIED));
        taskRepository.delete(task);
    }

    @Transactional
    public void toggleCheck(Long memberId, Long taskId) {
        Task task = taskRepository.findByTaskIdAndMemberId(taskId, memberId)
                .orElseThrow(() -> new BusinessException(PlanErrorCode.ACCESS_DENIED));
        task.toggleCheck();
    }
}
