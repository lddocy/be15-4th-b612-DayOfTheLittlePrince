package littleprince.ai.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.ai.query.dto.response.GeneratePlaneResponse;
import littleprince.ai.query.service.AiQueryService;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AI 투두 추천")
@RestController
@RequiredArgsConstructor()
@RequestMapping("/ai")
public class AiQueryController {

    private final AiQueryService aiQueryService;

    @Operation(summary = "AI 추천 요청",description = "사용자는 AI 추천 받기 버튼을 클릭하여 내일 할 일 목록을 추천 받을 수 있다.")
    @GetMapping("/plan")
    public ResponseEntity<ApiResponse<GeneratePlaneResponse>> generateAiPlan(
            @AuthenticationPrincipal CustomUserDetail userDetail
            ){
        GeneratePlaneResponse response = aiQueryService.generateAiPlan(userDetail);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
