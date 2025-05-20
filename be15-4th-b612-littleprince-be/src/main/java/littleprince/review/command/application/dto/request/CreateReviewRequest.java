package littleprince.review.command.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateReviewRequest {
    @NotNull
    private String reviewContent;
}
