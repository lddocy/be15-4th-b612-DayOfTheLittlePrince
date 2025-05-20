package littleprince.member.command.mapper;

import littleprince.member.command.domain.aggregate.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberCommandMapper {
    void insertMember(MemberDTO member);

    void updateLevelAndExp(MemberDTO member);
}
