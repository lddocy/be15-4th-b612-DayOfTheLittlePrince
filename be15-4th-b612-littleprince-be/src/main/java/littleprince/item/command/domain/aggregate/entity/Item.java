package littleprince.item.command.domain.aggregate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 👈 이걸 추가
@AllArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    private Long itemId;

    private String itemName;

    private BigDecimal xCoord;

    private BigDecimal yCoord;

    private BigDecimal zCoord;

    private int level;

    private BigDecimal size;

    @Enumerated(EnumType.STRING)
    private FixedItem isFixed;
}
