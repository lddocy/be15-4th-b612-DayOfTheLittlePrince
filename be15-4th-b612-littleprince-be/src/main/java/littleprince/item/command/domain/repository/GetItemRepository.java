package littleprince.item.command.domain.repository;

import littleprince.item.command.domain.aggregate.entity.GetItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GetItemRepository extends JpaRepository<GetItem, Long> {
    boolean existsByMemberIdAndItemId(Long memberId, Long itemId);
   Optional<GetItem> findByMemberIdAndItemId(Long memberId, Long itemId);
}
