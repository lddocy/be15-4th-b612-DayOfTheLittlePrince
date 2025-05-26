package littleprince.plan.command.application.controller;

import jakarta.validation.Valid;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.command.application.dto.request.CreateLongTodoRequestDto;
import littleprince.plan.command.application.service.LongTodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans/long-todos")
@Tag(name = "장기 투두 API", description = "장기 프로젝트 기반 투두 생성 API")
public class LongTodoCommandController {

    private final LongTodoCommandService longTodoCommandService;

    @PostMapping
    @Operation(summary = "장기 투두 생성", description = "하나의 프로젝트와 연결된 여러 개의 체크리스트 항목을 생성합니다.")
    public ResponseEntity<ApiResponse<Long>> createProjectWithTasks(
            @AuthenticationPrincipal CustomUserDetail user,
            @RequestBody @Valid CreateLongTodoRequestDto dto
    ) {
        Long projectId = longTodoCommandService.createProjectWithTasks(user.getMemberId(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(projectId));
    }
}