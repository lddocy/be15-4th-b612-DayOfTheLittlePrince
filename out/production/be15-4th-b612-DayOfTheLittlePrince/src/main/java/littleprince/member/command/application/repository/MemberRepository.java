package littleprince.member.command.application.repository;

import littleprince.common.domain.StatusType;
import littleprince.member.command.domain.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Object> findByEmailAndIsDeleted(String foundEmail, StatusType isDeleted);
}
