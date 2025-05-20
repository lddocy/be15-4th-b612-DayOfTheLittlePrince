package littleprince.item.command.domain.repository;

import littleprince.item.command.domain.aggregate.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaItemRepository extends JpaRepository<Item, Long> {
}
