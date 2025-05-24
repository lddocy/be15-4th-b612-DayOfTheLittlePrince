package littleprince.review.command.application.service;

import littleprince.review.command.application.dto.request.CreateReviewRequest;
import littleprince.review.command.domain.aggregate.Review;
import littleprince.review.command.domain.repository.JpaReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReviewCommandServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private JpaReviewRepository reviewRepository;

    @InjectMocks
    private ReviewCommandService reviewCommandService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrUpdateReview_기존회고없으면_새로등록() {
        // given
        Long memberId = 1L;
        Date date = Date.valueOf(LocalDate.now());
        String content = "오늘은 좋은 하루였다.";

        CreateReviewRequest request = CreateReviewRequest.builder()
                .reviewContent(content)
                .build();

        Review mappedReview = Review.builder()
                .memberId(memberId)
                .reviewDate(date)
                .reviewContent(content)
                .build();

        when(reviewRepository.findByMemberIdAndReviewDate(memberId, date)).thenReturn(Optional.empty());
        when(modelMapper.map(request, Review.class)).thenReturn(mappedReview);
        when(reviewRepository.save(any(Review.class))).thenReturn(mappedReview);

        // when
        reviewCommandService.createOrUpdateReview(memberId, date, request);

        // then
        verify(reviewRepository).save(argThat(savedReview ->
                savedReview.getMemberId().equals(memberId) &&
                        savedReview.getReviewDate().equals(date) &&
                        savedReview.getReviewContent().equals(content)
        ));
    }


    @Test
    void createOrUpdateReview_기존회고있으면_수정() {
        // given
        Long memberId = 1L;
        Date date = Date.valueOf(LocalDate.now());
        CreateReviewRequest request = new CreateReviewRequest("수정된 내용입니다.");

        Review existingReview = Review.builder()
                .memberId(memberId)
                .reviewDate(date)
                .reviewContent("이전 내용")
                .build();

        when(reviewRepository.findByMemberIdAndReviewDate(memberId, date)).thenReturn(Optional.of(existingReview));

        // when
        reviewCommandService.createOrUpdateReview(memberId, date, request);

        // then
        assertThat(existingReview.getReviewContent()).isEqualTo("수정된 내용입니다.");
        verify(reviewRepository, never()).save(any()); // 수정만 하고 저장은 하지 않음
    }
}
