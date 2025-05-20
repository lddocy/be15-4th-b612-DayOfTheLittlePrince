package littleprince.member.command.application.service;


import jakarta.validation.Valid;
import littleprince.member.command.application.dto.request.ExpRequest;
import littleprince.member.command.application.dto.request.SignupRequest;
import littleprince.member.command.application.dto.response.ExpResponse;

public interface MemberCommandService {
    void signup(@Valid SignupRequest request);

    ExpResponse addExp(ExpRequest request);

}
