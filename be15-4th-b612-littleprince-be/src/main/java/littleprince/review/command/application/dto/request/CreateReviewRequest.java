package littleprince.review.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateReviewRequest {
    @NotNull(message = "내용을 채워주세요")
    private String reviewContent;
}
