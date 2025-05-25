package littleprince.review.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "회고")
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    /* 리뷰 조회 */
    @Operation(summary = "회고 조회",description = "사용자는 당일의 일기를 조회할 수 있다.")
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
