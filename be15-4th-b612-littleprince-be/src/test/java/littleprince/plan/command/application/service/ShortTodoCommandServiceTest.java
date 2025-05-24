package littleprince.plan.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.plan.command.application.dto.request.CreateShortTodoRequestDto;
import littleprince.plan.command.domain.aggregate.Task;
import littleprince.plan.command.mapper.ShortTodoCommandMapper;
import littleprince.plan.command.repository.TaskRepository;
import littleprince.plan.exception.PlanErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShortTodoCommandServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private ShortTodoCommandMapper mapper;

    @InjectMocks
    private ShortTodoCommandService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_정상_저장() {
        CreateShortTodoRequestDto dto = CreateShortTodoRequestDto.builder()
                .content("할 일")
                .date(LocalDate.now())
                .build();

        Task task = Task.builder()
                .taskId(1L)
                .memberId(1L)
                .content("할 일")
                .build();

        when(mapper.toEntity(dto, 1L)).thenReturn(task);

        service.create(1L, dto);

        verify(taskRepository).save(task);
    }

    @Test
    void deleteShortTodo_정상_삭제() {
        Task task = Task.builder().taskId(1L).memberId(1L).build();

        when(taskRepository.findByTaskIdAndMemberId(1L, 1L)).thenReturn(Optional.of(task));

        service.deleteShortTodo(1L, 1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void deleteShortTodo_본인_아니면_예외() {
        when(taskRepository.findByTaskIdAndMemberId(1L, 1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteShortTodo(1L, 1L))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.ACCESS_DENIED.getMessage());
    }

    @Test
    void toggleCheck_정상_토글() {
        Task task = Task.builder().taskId(1L).memberId(1L).build();

        when(taskRepository.findByTaskIdAndMemberId(1L, 1L)).thenReturn(Optional.of(task));

        service.toggleCheck(1L, 1L);

        verify(taskRepository).findByTaskIdAndMemberId(1L, 1L);
    }

    @Test
    void toggleCheck_본인_아니면_예외() {
        when(taskRepository.findByTaskIdAndMemberId(1L, 1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.toggleCheck(1L, 1L))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining(PlanErrorCode.ACCESS_DENIED.getMessage());
    }
}
