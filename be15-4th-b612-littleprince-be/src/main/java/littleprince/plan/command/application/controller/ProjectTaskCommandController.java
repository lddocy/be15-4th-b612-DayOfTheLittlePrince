package littleprince.plan.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.command.application.dto.request.CreateProjectTaskRequestDto;
import littleprince.plan.command.application.service.ProjectTaskCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans/project-tasks")
public class ProjectTaskCommandController {

    private final ProjectTaskCommandService projectTaskCommandService;

    @PostMapping("/{projectId}/tasks")
    @Operation(summary = "장기 프로젝트 하위 체크리스트 생성", description = "특정 프로젝트에 여러 개의 할 일을 추가합니다.")
    public ResponseEntity<ApiResponse<Void>> createProjectTasks(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long projectId,
            @RequestBody @Valid CreateProjectTaskRequestDto dto
            ){
        projectTaskCommandService.createProjectTasks(userDetail.getMemberId(),projectId, dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @PatchMapping("/{taskId}/check")
    @Operation(summary = "장기 투두 체크 상태 토글", description = "장기 프로젝트에 속한 체크리스트 항목의 체크 상태를 토글합니다.")
    public ResponseEntity<ApiResponse<Void>> toggleProjectTaskCheck(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long taskId
    ) {
        projectTaskCommandService.toggleProjectTaskCheck(userDetail.getMemberId(), taskId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "장기 투두 체크리스트 항목 삭제", description = "장기 프로젝트에 속한 체크리스트 항목(task)를 삭제합니다.")
    public ResponseEntity<ApiResponse<Void>> deleteProjectTask(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long taskId
    ) {
        projectTaskCommandService.deleteProjectTask(userDetail.getMemberId(), taskId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}