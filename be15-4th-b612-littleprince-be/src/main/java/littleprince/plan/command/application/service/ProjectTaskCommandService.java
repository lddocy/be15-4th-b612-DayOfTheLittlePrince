package littleprince.plan.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.plan.command.application.dto.request.CreateProjectTaskRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.command.repository.TaskRepository;
import littleprince.plan.exception.PlanErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTaskCommandService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public void createProjectTasks(Long memberId, Long projectId, CreateProjectTaskRequestDto dto) {
        // 1. project 존재 여부 확인
        if (!projectRepository.existsById(projectId)) {
            throw new IllegalArgumentException("해당 projectId는 존재하지 않습니다.");
        }

        // 2. task 리스트 생성
        List<Task> tasks = dto.getTasks().stream()
                .map(taskDto -> Task.builder()
                        .memberId(memberId)
                        .projectId(projectId)
                        .content(taskDto.getContent())
                        .date(taskDto.getDate())
                        .build()
                ).toList();

        // 3. 저장
        taskRepository.saveAll(tasks);
    }

    @Transactional
    public void toggleProjectTaskCheck(Long memberId, Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new BusinessException(PlanErrorCode.NOT_FOUND_TASK));

        // 본인 투두인지 확인
        if(!task.getMemberId().equals(memberId)) {
            throw new BusinessException(PlanErrorCode.ACCESS_DENIED);
        }

        // 장기 프로젝트인지 확인
        if(task.getProjectId() == null) {
            throw new BusinessException(PlanErrorCode.INVALID_PROJECT_TASK);
        }

        task.toggleCheck();
    }

    @Transactional
    public void deleteProjectTask(Long memberId, Long taskId) {
        Task task = taskRepository.findByTaskIdAndMemberId(taskId, memberId)
                .orElseThrow(() -> new BusinessException(PlanErrorCode.ACCESS_DENIED));
        taskRepository.delete(task);
    }
}
