package littleprince.auth.command.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangePasswordRequest {

    @NotBlank
    public String uuid;

    @NotBlank
    public String password;

    @NotBlank
    public String confirmPassword;
}
