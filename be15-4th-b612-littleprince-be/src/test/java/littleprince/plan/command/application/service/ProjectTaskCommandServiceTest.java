package littleprince.plan.command.application.service;

import littleprince.common.domain.StatusType;
import littleprince.common.exception.BusinessException;
import littleprince.plan.command.application.dto.request.CreateProjectTaskRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.command.repository.TaskRepository;
import littleprince.plan.exception.PlanErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectTaskCommandServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ProjectTaskCommandService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProjectTasks_존재하지_않는_프로젝트면_예외() {
        // given
        when(projectRepository.existsById(1L)).thenReturn(false);
        CreateProjectTaskRequestDto dto = CreateProjectTaskRequestDto.builder()
                .tasks(List.of(
                        CreateProjectTaskRequestDto.ProjectTaskDto.builder()
                                .content("test")
                                .date(LocalDate.now())
                                .build()
                ))
                .build();

        // when & then
        assertThatThrownBy(() -> service.createProjectTasks(1L, 1L, dto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("projectId");
    }

    @Test
    void toggleProjectTaskCheck_정상_체크_토글() {
        // given
        Task task = Task.builder()
                .taskId(10L)
                .memberId(1L)
                .projectId(100L)
                .checked(StatusType.N)
                .build();

        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        // when
        service.toggleProjectTaskCheck(1L, 10L);

        // then
        assertThat(task.getChecked()).isEqualTo(StatusType.Y);
    }

    @Test
    void toggleProjectTaskCheck_본인_아니면_예외() {
        Task task = Task.builder()
                .taskId(10L)
                .memberId(2L)
                .projectId(100L)
                .checked(StatusType.N)
                .build();

        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        assertThatThrownBy(() -> service.toggleProjectTaskCheck(1L, 10L))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.ACCESS_DENIED.getMessage());
    }

    @Test
    void toggleProjectTaskCheck_장기프로젝트가_아니면_예외() {
        Task task = Task.builder()
                .taskId(10L)
                .memberId(1L)
                .projectId(null) // 단기 task
                .checked(StatusType.N)
                .build();

        when(taskRepository.findById(10L)).thenReturn(Optional.of(task));

        assertThatThrownBy(() -> service.toggleProjectTaskCheck(1L, 10L))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.INVALID_PROJECT_TASK.getMessage());
    }

    @Test
    void deleteProjectTask_정상_삭제() {
        Task task = Task.builder()
                .taskId(10L)
                .memberId(1L)
                .build();

        when(taskRepository.findByTaskIdAndMemberId(10L, 1L)).thenReturn(Optional.of(task));

        service.deleteProjectTask(1L, 10L);

        verify(taskRepository).delete(task);
    }

    @Test
    void deleteProjectTask_본인_아니면_예외() {
        when(taskRepository.findByTaskIdAndMemberId(10L, 1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteProjectTask(1L, 10L))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.ACCESS_DENIED.getMessage());
    }
}
