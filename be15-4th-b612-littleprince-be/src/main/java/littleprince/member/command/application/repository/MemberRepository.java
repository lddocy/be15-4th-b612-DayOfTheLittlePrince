package littleprince.member.command.application.repository;

import littleprince.member.command.domain.aggregate.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}

