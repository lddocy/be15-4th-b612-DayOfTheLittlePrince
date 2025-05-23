package littleprince.member.query.dto.response;

import littleprince.member.query.dto.GetExpHistoryDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetExpHistoryResponse {
    private List<GetExpHistoryDTO> expHistoryDTO;
}
