package littleprince.plan.command.application.controller;

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
    public ResponseEntity<ApiResponse<Void>> createProjectTasks(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long projectId,
            @RequestBody @Valid CreateProjectTaskRequestDto dto
            ){
        projectTaskCommandService.createProjectTasks(userDetail.getMemberId(),projectId, dto);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}