package littleprince.review.command.application.service;

import littleprince.review.command.application.dto.request.CreateReviewRequest;
import littleprince.review.command.domain.aggregate.Review;
import littleprince.review.command.domain.repository.JpaReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

    private final ModelMapper modelMapper;
    private final JpaReviewRepository jpaReviewJpaRepository;

    /* 리뷰 등록 */
    @Transactional
    public void createReview(Long memberId, Date date, CreateReviewRequest createReviewRequest) {

        Review review = modelMapper.map(createReviewRequest, Review.class);

        review.setMemberId(memberId);
        review.setReviewDate(date);

        jpaReviewJpaRepository.save(review);
    }
}
