package littleprince.item.command.application.controller;

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
    public ResponseEntity<ApiResponse<Void>> addItem(
            @AuthenticationPrincipal CustomUserDetail userDetail
    ) {
        Long memberId = userDetail.getMemberId();
        itemCommandService.addItem(memberId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/{itemId}/toggle")
    public ResponseEntity<ApiResponse<Void>> toggleItemHidden(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long itemId
    ) {
        Long memberId = userDetail.getMemberId();
        itemCommandService.toggleItemHidden(memberId, itemId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
