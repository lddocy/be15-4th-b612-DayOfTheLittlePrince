package littleprince.auth.command.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "비밀번호 변경 요청 DTO")
@Getter
@Builder
public class ChangePasswordRequest {

    @Schema(description = "고유 식별자")
    @NotBlank
    public String uuid;

    @Schema(description = "변경된 비밀번호")
    @NotBlank
    public String password;

    @Schema(description = "변경된 비밀번호 확인")
    @NotBlank
    public String confirmPassword;
}
