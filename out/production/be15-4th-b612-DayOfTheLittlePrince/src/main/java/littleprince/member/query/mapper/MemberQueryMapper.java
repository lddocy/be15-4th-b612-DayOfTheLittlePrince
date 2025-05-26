package littleprince.member.query.mapper;

import littleprince.member.command.domain.aggregate.MemberDTO;
import littleprince.member.query.dto.FindMemberDTO;
import littleprince.member.query.dto.MemberExpDTO;
import littleprince.member.query.dto.response.MemberInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberQueryMapper {
    Optional<FindMemberDTO> findMemberByEmail(String email);
    Optional<MemberDTO> findById(Long memberId);
    Optional<MemberInfoResponse> findMemberByMemberId(Long memberId);

    List<MemberExpDTO> findAllExpInfo();
}
