package littleprince.member.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExpResponse {
    private Long memberId;
    private int updatedExp;
    private int updatedLevel;
    private boolean levelUp;
}

