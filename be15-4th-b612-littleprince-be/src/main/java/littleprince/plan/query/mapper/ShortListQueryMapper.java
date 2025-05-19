package littleprince.plan.query.mapper;

import littleprince.plan.query.dto.response.ShortListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;

@Mapper
public interface ShortListQueryMapper {

    /* 단기 리스트 목록 조회 */
    List<ShortListDTO> getShortList(Long memberId, Date date);
}
