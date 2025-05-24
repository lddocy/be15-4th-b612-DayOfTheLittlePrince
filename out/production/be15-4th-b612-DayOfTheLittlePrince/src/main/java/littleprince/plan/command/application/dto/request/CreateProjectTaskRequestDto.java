package littleprince.plan.command.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Schema(description = "장기 프로젝트 하위 체크리스트 생성 요청 DTO")
public class CreateProjectTaskRequestDto {

    @NotNull
    @Schema(description = "체크리스트 항목 목록", example = "[{\"content\": \"요구사항 정리\", \"date\": \"2025-05-21\"}]")
    private List<ProjectTaskDto> tasks;

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    @Schema(description = "개별 체크리스트 항목 DTO")
    public static class ProjectTaskDto {
        @NotBlank
        @Schema(description = "할 일 내용", example = "요구사항 명세서 작성")
        private String content;

        @NotNull
        @Schema(description = "일정 날짜", example = "2025-05-21")
        private LocalDate date;
    }
}