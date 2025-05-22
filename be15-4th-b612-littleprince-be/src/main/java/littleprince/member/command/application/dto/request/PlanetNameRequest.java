package littleprince.member.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlanetNameRequest {
    @NotNull
    private String planetName;
}
