package littleprince.review.query.service;

import littleprince.review.query.dto.response.ReviewDetailResponse;
import littleprince.review.query.mapper.ReviewQueryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Date;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ReviewQueryServiceTest {

    @Mock
    private ReviewQueryMapper reviewQueryMapper;

    @InjectMocks
    private ReviewQueryService reviewQueryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReviewDetail_shouldReturnReviewDetailResponse() {
        // given
        Long memberId = 1L;
        Date date = Date.valueOf(LocalDate.now());

        ReviewDetailResponse expectedResponse = ReviewDetailResponse.builder()
                .reviewContent("회고 내용")
                .build();

        when(reviewQueryMapper.getReviewDetail(memberId, date)).thenReturn(expectedResponse);

        // when
        ReviewDetailResponse result = reviewQueryService.getReviewDetail(memberId, date);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getReviewContent()).isEqualTo("회고 내용");
        verify(reviewQueryMapper).getReviewDetail(memberId, date);
    }
}
