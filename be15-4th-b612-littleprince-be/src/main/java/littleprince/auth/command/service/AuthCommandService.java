package littleprince.auth.command.service;

import littleprince.config.security.model.CustomUserDetail;

public interface AuthCommandService {
    void logout(CustomUserDetail userDetail, String refreshToken);
}
