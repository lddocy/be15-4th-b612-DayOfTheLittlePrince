package littleprince.plan.query.mapper;

import littleprince.plan.query.dto.ShortListDTO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Mapper
public interface ShortListQueryMapper {

    /* 단기 리스트 목록 조회 */
    List<ShortListDTO> getShortList(Long memberId, Date date);
}
