package littleprince.auth.query.dto.request;


import jakarta.validation.constraints.NotBlank;
import littleprince.member.annotation.ValidEmail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class LoginRequest {

    @ValidEmail
    private final String email;

    @NotBlank
    private final String password;
}
