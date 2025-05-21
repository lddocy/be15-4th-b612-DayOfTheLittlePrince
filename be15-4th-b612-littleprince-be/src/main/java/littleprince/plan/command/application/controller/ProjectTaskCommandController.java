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
@RequestMapping("/plans/projects/{projectId}/tasks")
public class ProjectTaskCommandController {

    private final ProjectTaskCommandService projectTaskCommandService;

    @PostMapping
    @Operation(summary = "장기 프로젝트 하위 체크리스트 생성", description = "특정 프로젝트에 여러 개의 할 일을 추가합니다.")
    public ResponseEntity<ApiResponse<Void>> createProjectTasks(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long projectId,
            @RequestBody @Valid CreateProjectTaskRequestDto dto
            ){
        projectTaskCommandService.createProjectTasks(userDetail.getMemberId(),projectId, dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}