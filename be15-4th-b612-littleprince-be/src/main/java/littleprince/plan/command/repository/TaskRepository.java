package littleprince.plan.command.repository;

import littleprince.plan.command.domain.aggregate.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskIdAndMemberId(Long taskId, Long memberId);
}
