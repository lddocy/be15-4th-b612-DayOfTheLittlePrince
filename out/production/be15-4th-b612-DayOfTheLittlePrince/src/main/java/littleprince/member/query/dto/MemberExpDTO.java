package littleprince.member.query.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberExpDTO {
    private Long memberId;
    private Integer exp;
    private Integer level;
}
