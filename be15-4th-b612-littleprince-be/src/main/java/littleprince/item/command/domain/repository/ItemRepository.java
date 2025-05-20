package littleprince.item.command.domain.repository;

import littleprince.item.command.domain.aggregate.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByLevel(int level);
}
