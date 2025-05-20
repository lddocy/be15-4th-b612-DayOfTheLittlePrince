package littleprince.review.query.mapper;

import littleprince.review.query.dto.response.ReviewDetailResponse;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;

@Mapper
public interface ReviewQueryMapper {
    /* 리뷰 조회 */
    ReviewDetailResponse getReviewDetail(Long memberId, Date date);
}
