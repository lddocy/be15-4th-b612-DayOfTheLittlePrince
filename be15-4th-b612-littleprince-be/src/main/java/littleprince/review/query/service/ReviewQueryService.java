package littleprince.review.query.service;

import littleprince.review.query.dto.response.ReviewDetailResponse;
import littleprince.review.query.mapper.ReviewQueryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ReviewQueryService {

    private final ReviewQueryMapper reviewQueryMapper;

    /* 리뷰 조회 */
    public ReviewDetailResponse getReviewDetail(Long memberId, Date date) {
        return reviewQueryMapper.getReviewDetail(memberId, date);
    }
}
