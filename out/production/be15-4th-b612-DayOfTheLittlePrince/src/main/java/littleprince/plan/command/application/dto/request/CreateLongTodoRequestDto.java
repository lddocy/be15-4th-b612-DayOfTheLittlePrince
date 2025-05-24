package littleprince.plan.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Schema(description = "장기 투두 프로젝트 생성 요청 DTO")
@Builder
public class CreateLongTodoRequestDto {

    @Schema(description = "프로젝트 제목", example = "프론트엔드 세팅 완료하기")
    @NotBlank
    private String title;

    @Schema(description = "시작 날짜", example = "2025-05-14")
    @NotNull
    private LocalDate startDate;

    @Schema(description = "종료 날짜", example = "2025-08-20")
    @NotNull
    private LocalDate endDate;
}