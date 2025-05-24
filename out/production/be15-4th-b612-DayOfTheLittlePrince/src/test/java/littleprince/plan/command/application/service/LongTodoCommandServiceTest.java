package littleprince.plan.command.application.service;

import littleprince.plan.command.application.dto.request.CreateLongTodoRequestDto;
import littleprince.plan.command.domain.aggregate.Project;
import littleprince.plan.command.repository.ProjectRepository;
import littleprince.plan.command.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class LongTodoCommandServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private LongTodoCommandService longTodoCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProjectWithTasks_프로젝트가_정상적으로_생성된다() throws Exception {
        // given
        Long memberId = 1L;
        CreateLongTodoRequestDto dto = CreateLongTodoRequestDto.builder()
                .title("장기 플랜")
                .startDate(LocalDate.of(2025, 5, 1))
                .endDate(LocalDate.of(2025, 5, 31))
                .build();

        when(projectRepository.save(any(Project.class))).thenAnswer(invocation -> {
            Project p = invocation.getArgument(0);
            java.lang.reflect.Field field = Project.class.getDeclaredField("projectId");
            field.setAccessible(true);
            field.set(p, 100L); // 💡 실제 서비스 내 project에 ID를 세팅함
            return p;
        });

        // when
        Long resultId = longTodoCommandService.createProjectWithTasks(memberId, dto);

        // then
        assertThat(resultId).isEqualTo(100L);
    }
}
