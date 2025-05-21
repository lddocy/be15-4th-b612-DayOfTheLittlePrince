package littleprince.plan.command.application.service;

import littleprince.plan.command.application.dto.request.CreateProjectTaskRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.command.repository.TaskRepository;
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
}
