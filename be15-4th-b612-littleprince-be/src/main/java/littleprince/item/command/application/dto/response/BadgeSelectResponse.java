package littleprince.item.command.application.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BadgeSelectResponse {
    private Long memberId;
    private Long selectedBadgeId;
    private String message; // 예: "칭호가 성공적으로 선택되었습니다."
}