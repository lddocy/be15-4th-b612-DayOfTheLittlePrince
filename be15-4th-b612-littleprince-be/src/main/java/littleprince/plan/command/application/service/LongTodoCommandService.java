package littleprince.plan.command.application.service;

import jakarta.transaction.Transactional;
import littleprince.plan.command.application.dto.request.CreateLongTodoRequestDto;
import littleprince.plan.command.domain.aggregate.Project;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.command.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LongTodoCommandService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    @Transactional
    public Long createProjectWithTasks(Long memberId, CreateLongTodoRequestDto dto) {
        // 프로젝트 생성 및 저장
        Project project = Project.builder()
                .memberId(memberId)
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();

        projectRepository.save(project);
        return project.getProjectId();
    }
}