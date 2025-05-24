package littleprince.member.command.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpHistoryCommandMapper {
    void insertExpHistory(@Param("memberId") Long memberId, @Param("expPoint") int expPoint);


    void insertExpHistoryId(@Param("memberId") Long memberId, @Param("expPoint") int expPoint, Long projectId);
}