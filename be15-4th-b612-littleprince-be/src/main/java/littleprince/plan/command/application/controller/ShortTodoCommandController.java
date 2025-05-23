package littleprince.plan.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.command.application.dto.request.CreateShortTodoRequestDto;
import littleprince.plan.command.application.service.ShortTodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans/short-todos")
@Tag(name = "단기 투두 리스트")
public class ShortTodoCommandController {
    private final ShortTodoCommandService shortTodoCommandService;

    @Operation(summary = "단기 플랜 생성",description = "사용자는 하루치 플랜을 작성할 수 있습니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createShortTodo(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @RequestBody @Valid CreateShortTodoRequestDto dto
            ){
        shortTodoCommandService.create(userDetail.getMemberId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(null));
    }

    @Operation(summary = "단기 플랜 삭제",description = "사용자는 하루치 플랜을 삭제할 수 있습니다.")
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ApiResponse<Void>> deleteShortTodo(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long taskId
    ) {
        shortTodoCommandService.deleteShortTodo(userDetail.getMemberId(), taskId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/{taskId}/check")
    @Operation(summary = "체크 상태 토글", description = "해당 단기 투두의 체크 상태를 토글합니다.")
    public ResponseEntity<ApiResponse<Void>> toggleCheck(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long taskId
    ) {
        shortTodoCommandService.toggleCheck(userDetail.getMemberId(), taskId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
