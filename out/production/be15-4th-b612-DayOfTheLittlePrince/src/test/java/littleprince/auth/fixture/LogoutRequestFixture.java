package littleprince.auth.fixture;

import littleprince.config.security.model.CustomUserDetail;

public class LogoutRequestFixture {
    public static LogoutRequest invalidTokenRequest() {
        CustomUserDetail userDetail = CustomUserDetail.builder()
                .memberId(1L)
                .email("user01@naver.com")
                .password("pass01")
                .build();

        return new LogoutRequest(userDetail, "1234");
    }

    public static LogoutRequest diffTokenRequest() {
        CustomUserDetail userDetail = CustomUserDetail.builder()
                .memberId(1L)
                .email("user01@naver.com")
                .password("pass01")
                .build();
        return new LogoutRequest(userDetail, "1234");
    }

    public static LogoutRequest validRequest(){
        CustomUserDetail userDetail = CustomUserDetail.builder()
                .memberId(1L)
                .email("user01@naver.com")
                .password("pass01")
                .build();

        return new LogoutRequest(userDetail, "1234");
    }

}
