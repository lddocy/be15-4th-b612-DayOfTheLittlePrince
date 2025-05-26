package littleprince.plan.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.plan.command.domain.aggregate.Project;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.exception.PlanErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectCommandServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectCommandService projectCommandService;

    private final Long projectId = 1L;
    private final Long ownerId = 100L;
    private final Long strangerId = 999L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteProject_프로젝트가_없으면_예외() {
        // given
        when(projectRepository.findById(projectId)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> projectCommandService.deleteProject(ownerId, projectId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.PROJECT_NOT_FOUND.getMessage());
    }

    @Test
    void deleteProject_다른_사용자가_삭제하려_하면_예외() {
        // given
        Project project = Project.builder()
                .projectId(projectId)
                .memberId(ownerId) // 다른 사람의 프로젝트
                .build();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // when & then
        assertThatThrownBy(() -> projectCommandService.deleteProject(strangerId, projectId))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.ACCESS_DENIED.getMessage());
    }

    @Test
    void deleteProject_정상_삭제() {
        // given
        Project project = Project.builder()
                .projectId(projectId)
                .memberId(ownerId)
                .build();

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        // when
        projectCommandService.deleteProject(ownerId, projectId);

        // then
        verify(projectRepository).delete(project);
    }
}
