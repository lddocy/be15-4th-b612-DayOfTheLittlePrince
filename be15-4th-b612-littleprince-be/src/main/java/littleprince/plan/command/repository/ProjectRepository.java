package littleprince.plan.command.repository;

import littleprince.plan.command.domain.aggregate.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}