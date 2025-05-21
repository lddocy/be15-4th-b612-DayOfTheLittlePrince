package littleprince.plan.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "장기 프로젝트 하위 체크리스트 생성 요청 DTO")
public class CreateProjectTaskRequestDto {
    @NotNull
    private List<ProjectTaskDto> tasks;

    @Getter
    @NoArgsConstructor
    public static class ProjectTaskDto {
        @NotBlank
        private String content;

        @NotNull
        private LocalDate date;
    }
}