package littleprince.member.query.mapper;

import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.dto.response.MemberInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface MemberQueryMapper {
    Optional<FindMemberDTO> findMemberByEmail(String email);
    MemberInfoResponse findMemberByMemberId(Long memberId);
}
