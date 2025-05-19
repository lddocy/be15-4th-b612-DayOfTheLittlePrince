package littleprince.plan.query.mapper;

import littleprince.plan.query.dto.response.LongListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface LongListQueryMapper {
    /* 장기 플랜 목록 조회 */
    List<LongListDTO> getLongList(Long memberId, Date date);
}
