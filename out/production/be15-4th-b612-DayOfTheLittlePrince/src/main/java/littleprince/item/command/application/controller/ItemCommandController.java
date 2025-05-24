package littleprince.item.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.item.command.application.service.ItemCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name="아이템")
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemCommandController {
    private final ItemCommandService itemCommandService;

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
