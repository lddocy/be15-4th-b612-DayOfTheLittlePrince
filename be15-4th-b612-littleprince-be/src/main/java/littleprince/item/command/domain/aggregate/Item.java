package littleprince.item.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "item")
public class Item {
    @Id
    private int itemId;

    private String itemName;

    private BigDecimal xCoord;

    private BigDecimal yCoord;

    private BigDecimal zCoord;

    private int level;

    private BigDecimal size;

    @Enumerated(EnumType.STRING)
    private FixedItem isFixed;
}
