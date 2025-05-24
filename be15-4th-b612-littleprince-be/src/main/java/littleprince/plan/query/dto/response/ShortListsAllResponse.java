package littleprince.plan.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ShortListsAllResponse {

    private final List<ShortListsAllDTO> shortListsAll;

}