package littleprince.auth.fixture;

import littleprince.config.security.model.CustomUserDetail;


public class LogoutRequest {
    public LogoutRequest(CustomUserDetail userDetail, String refreshToken) {
        this.userDetail = userDetail;
        this.refreshToken = refreshToken;
    }

    private final CustomUserDetail userDetail;
    private final String refreshToken;

    public CustomUserDetail getUserDetail() {
        return userDetail;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
