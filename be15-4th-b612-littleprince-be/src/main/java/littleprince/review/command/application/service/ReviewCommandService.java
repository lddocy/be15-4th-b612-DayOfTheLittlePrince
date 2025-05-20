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
    private final JpaReviewRepository jpaReviewRepository;

    /* 리뷰 등록 */
    @Transactional
    public void createOrUpdateReview(Long memberId, Date date, CreateReviewRequest createReviewRequest) {

        /* 해당 date 에 회고가 등록되어있는지 확인 등록 되어있으면 update */
        Review review = (Review) jpaReviewRepository
                .findByMemberIdAndReviewDate(memberId, date)
                .orElseGet(() -> {
                    /* 해당 date 에 회고가 등록되어있지않으면 새로 등록 */
                    Review newReview = modelMapper.map(createReviewRequest, Review.class);
                    newReview.setMemberId(memberId);
                    newReview.setReviewDate(date);
                    return jpaReviewRepository.save(newReview);
                });

        review.updateReview(createReviewRequest.getReviewContent());
    }
}
