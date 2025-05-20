package littleprince.item.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.item.query.dto.response.ItemListResponse;
import littleprince.item.query.service.ItemQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="아이템")
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemQueryController {

    private final ItemQueryService itemQueryService;

    /* 사용자 보유 아이템 조회 */
    @GetMapping("/list")
    @Operation(summary = "아이템 리스트 조회", description = "사용자가 보유한 아이템 리스트를 조회합니다.")
    public ResponseEntity<ApiResponse<ItemListResponse>> getItemList(
            @AuthenticationPrincipal CustomUserDetail customUserDetail
    ) {
        Long memberId =  customUserDetail.getMemberId();
        ItemListResponse response = itemQueryService.getItemList(memberId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
