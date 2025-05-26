package littleprince.plan.command.repository;

import littleprince.plan.command.domain.aggregate.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectId(Long memberId);

    Project findByMemberId(Long memberId);


    Optional<Project> findByMemberIdAndEndDate(Long memberId, LocalDate yesterday);

    List<Project> findAllByMemberIdAndEndDate(Long memberId, LocalDate yesterday);
}