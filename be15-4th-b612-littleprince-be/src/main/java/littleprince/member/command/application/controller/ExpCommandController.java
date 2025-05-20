package littleprince.member.command.application.controller;

import littleprince.member.command.application.dto.request.ExpRequest;
import littleprince.member.command.application.dto.response.ExpResponse;
import littleprince.member.command.application.service.MemberCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp")
@RequiredArgsConstructor
public class ExpCommandController {

    private final MemberCommandService memberCommandService;

    @PostMapping("")
    public ResponseEntity<ExpResponse> addExp(@RequestBody ExpRequest request) {
        ExpResponse response = memberCommandService.addExp(request);
        return ResponseEntity.ok(response);
    }

}
