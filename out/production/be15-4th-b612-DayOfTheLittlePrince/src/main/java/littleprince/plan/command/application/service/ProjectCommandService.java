package littleprince.plan.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.plan.command.domain.aggregate.Project;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.exception.PlanErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectCommandService {

    private final ProjectRepository projectRepository;

    @Transactional
    public void deleteProject(Long memberId, Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BusinessException(PlanErrorCode.PROJECT_NOT_FOUND));

        if(!project.getMemberId().equals(memberId)) {
            throw new BusinessException(PlanErrorCode.ACCESS_DENIED);
        }

        projectRepository.delete(project);  // task는 ON DELETE CASCADE로 자동 삭제됨
    }
}
