package littleprince.famousquotes.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.famousquotes.query.service.FamousQuotesQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "명언")
@RestController
@RequiredArgsConstructor()
@RequestMapping("/famous-quotes")
public class FamousQuotesQueryController {

    private final FamousQuotesQueryService famousQuotesQueryService;


    @Operation(summary = "명언 조회",description = "사용자는 로그인 후 명언 조회를 할 수 있다.")
    @GetMapping
    public ResponseEntity<ApiResponse<String>> getRandomFamousQuotes(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        String response = famousQuotesQueryService.getFamousQuotes();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
