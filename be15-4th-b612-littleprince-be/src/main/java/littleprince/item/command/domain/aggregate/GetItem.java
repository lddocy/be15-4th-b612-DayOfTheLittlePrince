package littleprince.item.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "get_item")
public class GetItem {
    @Id
    private int memberId;

    private int itemId;

    @Enumerated(EnumType.STRING)
    private HiddenItem isHidden;

    private LocalDateTime createAt;
}
