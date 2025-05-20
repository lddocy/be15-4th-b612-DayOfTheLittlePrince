package littleprince.review.query.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Date;

@Builder
@Getter
public class ReviewDetailResponse {
    private Long reviewId;
    private String reviewContent;
    private Date date;
}
