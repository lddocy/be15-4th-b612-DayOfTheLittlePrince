package littleprince.review.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.review.command.application.dto.request.CreateReviewRequest;
import littleprince.review.command.application.service.ReviewCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@Tag(name = "회고")
@RestController
@RequestMapping("/plan/review")
@RequiredArgsConstructor
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    @Operation(summary = "회고 작성 or 업데이트", description = "해당요일에 회고를 작성한다. 이미 작성된 회고가 있으면 업데이트")
    @PostMapping("/{date}")
    public ResponseEntity<ApiResponse<Void>> createOrUpdateReview(
            @AuthenticationPrincipal CustomUserDetail customUserDetail,
            @PathVariable Date date,
            @RequestBody @Valid CreateReviewRequest createReviewRequest
    )
    {
        Long memberId = customUserDetail.getMemberId();
        reviewCommandService.createOrUpdateReview(memberId, date, createReviewRequest);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

}
