package littleprince.famousquotes.query.controller;

import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.famousquotes.query.service.FamousQuotesQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor()
@RequestMapping("/famous-quotes")
public class FamousQuotesQueryController {

    private final FamousQuotesQueryService famousQuotesQueryService;

    @GetMapping
    public ResponseEntity<ApiResponse<String>> getRandomFamousQuotes(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        String response = famousQuotesQueryService.getFamousQuotes();
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
