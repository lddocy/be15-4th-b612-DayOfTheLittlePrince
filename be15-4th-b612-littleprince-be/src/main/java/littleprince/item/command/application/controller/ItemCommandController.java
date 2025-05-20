package littleprince.item.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.item.command.application.service.ItemCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemCommandController {
    private final ItemCommandService itemCommandService;

    @PostMapping("/add")
    @Operation(summary = "아이템 추가", description = "사용자의 아이템 목록에 아이템을 추가합니다.")
    public ResponseEntity<ApiResponse<Void>> addItem(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        Long memberId = userDetail.getMemberId();
        itemCommandService.addItem(memberId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/{itemId}/toggle")
    @Operation(summary = "아이템 숨김 여부 토글", description = "아이템의 숨김 여부를 토글합니다.")
    public ResponseEntity<ApiResponse<Void>> toggleItemHidden(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long itemId
    ) {
        Long memberId = userDetail.getMemberId();
        itemCommandService.toggleItemHidden(memberId, itemId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
