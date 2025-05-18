package littleprince.auth.query.dto.request;


import jakarta.validation.constraints.NotBlank;
import littleprince.member.annotation.ValidEmail;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequest {

    @ValidEmail
    private final String email;

    @NotBlank
    private final String password;
}
