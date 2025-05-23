package littleprince.auth.query.service;

import jakarta.validation.Valid;
import littleprince.auth.command.dto.response.TokenResponse;
import littleprince.auth.query.dto.request.LoginRequest;

public interface AuthQueryService {
    TokenResponse login(@Valid LoginRequest request);

    TokenResponse reissue(String refreshToken);

    void findPassword(String email);
}
