package littleprince.auth.command.service;

import littleprince.auth.command.dto.request.ChangePasswordRequest;
import littleprince.config.security.model.CustomUserDetail;

public interface AuthCommandService {
    void logout(CustomUserDetail userDetail, String refreshToken);

    void changePassword(ChangePasswordRequest request);
}
