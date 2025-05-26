package littleprince.item.query.mapper;

import littleprince.item.command.domain.aggregate.entity.GetItem;
import littleprince.item.query.dto.ItemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemDTO> findItemListByLevel(Long memberId);
}
