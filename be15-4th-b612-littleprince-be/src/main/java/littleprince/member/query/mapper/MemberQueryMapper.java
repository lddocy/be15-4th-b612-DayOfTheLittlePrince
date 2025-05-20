package littleprince.member.query.mapper;

import littleprince.member.command.aggregate.domain.MemberDTO;
import littleprince.member.query.dto.FindMemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberQueryMapper {
    Optional<FindMemberDTO> findMemberByEmail(String email);

    Optional<MemberDTO> findById(Long memberId);
}
