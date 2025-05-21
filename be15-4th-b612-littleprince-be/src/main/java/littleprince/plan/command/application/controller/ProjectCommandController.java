package littleprince.plan.command.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import littleprince.common.dto.ApiResponse;
import littleprince.config.security.model.CustomUserDetail;
import littleprince.plan.command.application.service.ProjectCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/plans/projects")
public class ProjectCommandController {

    private final ProjectCommandService projectCommandService;

    @DeleteMapping("/{projectId}")
    @Operation(summary = "장기 프로젝트 삭제", description = "장기 프로젝트를 삭제합니다. 이때 해당 프로젝트에 속한 투두(Task)들도 함께 삭제됩니다.")
    public ResponseEntity<ApiResponse<Void>> deleteProject(
            @AuthenticationPrincipal CustomUserDetail userDetail,
            @PathVariable Long projectId
    ) {
        projectCommandService.deleteProject(userDetail.getMemberId(), projectId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
