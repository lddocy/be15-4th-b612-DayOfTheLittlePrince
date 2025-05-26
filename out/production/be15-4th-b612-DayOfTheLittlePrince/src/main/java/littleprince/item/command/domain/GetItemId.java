package littleprince.item.command.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetItemId implements Serializable {
    private Long memberId;
    private Long itemId;
}
