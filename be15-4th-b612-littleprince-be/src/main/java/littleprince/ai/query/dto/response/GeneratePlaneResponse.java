package littleprince.ai.query.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Schema(description = " AI 할 일 추천 응답 DTO ")
@Getter
@Builder
public class GeneratePlaneResponse {

    @Schema(description = "AI 할 일 추천", example = "자바 클래스 공부")
    private List<String> generatePlanList;
}
