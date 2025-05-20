package littleprince.member.query.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberInfoResponse {
    private Long memberId;
    
    private int exp;

    private int level;

    private String planetName;

    private String isSelected;

    private String badgeName;
}
