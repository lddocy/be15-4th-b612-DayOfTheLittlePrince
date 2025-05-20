package littleprince.item.command.domain.aggregate;

import jakarta.persistence.*;
import littleprince.item.command.domain.GetItemId;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@IdClass(GetItemId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetItem {
    @Id
    private Long memberId;

    @Id
    private Long itemId;

    @Enumerated(EnumType.STRING)
    private HiddenItem isHidden;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
