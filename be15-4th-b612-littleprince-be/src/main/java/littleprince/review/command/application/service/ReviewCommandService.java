package littleprince.review.command.application.service;

import littleprince.common.exception.BusinessException;
import littleprince.review.command.domain.aggregate.Review;
import littleprince.review.command.domain.repository.JpaReviewRepository;
import littleprince.review.exception.ReviewErrorCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ModelMapper modelMapper;
    private final JpaReviewRepository jpaReviewJpaRepository;

    /* 리뷰 등록 */
    public void createReview(Long memberId, Date date, String content) {

        Review review = modelMapper.map(content, Review.class);

        review.setMemberId(memberId);
        review.setRivewDate(date);

//        jpa

//        Review review = jpaReviewJpaRepository.findById(memberId).
//                orElseThrow( () -> new BusinessException(ReviewErrorCode.REVIEW_SUBMIT_FAILED));


    }
}
