package littleprince.plan.command.repository;

import littleprince.plan.command.domain.aggregate.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
