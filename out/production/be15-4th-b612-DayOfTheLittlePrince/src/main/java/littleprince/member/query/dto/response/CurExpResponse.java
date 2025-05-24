package littleprince.member.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CurExpResponse {
    private int currentExp;
    private int currentLevel;
    private int totalExpToNextLevel;
}

