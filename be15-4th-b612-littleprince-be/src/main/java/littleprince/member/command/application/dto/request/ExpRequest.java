package littleprince.member.command.application.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExpRequest {
    private Long memberId;
    private int expPoint;
}
