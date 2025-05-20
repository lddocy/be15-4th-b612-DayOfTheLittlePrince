package littleprince.review.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.review.query.dto.response.ReviewDetailResponse;
import littleprince.review.query.service.ReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    /* 리뷰 조회 */
    @GetMapping("/{date}")
    public ResponseEntity<ApiResponse<ReviewDetailResponse>> getReviewDetail(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Date date
    )
    {
        Long memberId = customUserDetail.getMemberId();

        ReviewDetailResponse response = reviewQueryService.getReviewDetail(memberId, date);

        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
