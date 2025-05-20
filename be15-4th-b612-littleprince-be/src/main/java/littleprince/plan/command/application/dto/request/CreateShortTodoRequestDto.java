package littleprince.plan.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateShortTodoRequestDto {
    @NotBlank
    private String content;

    @NotNull
    private LocalDate date;
}
