package littleprince.plan.command.mapper;

import littleprince.member.command.aggregate.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShortListCommandMapper {
    void insertMember(MemberDTO member);
}
