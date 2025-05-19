package littleprince.auth.fixture;

import littleprince.auth.query.dto.request.LoginRequest;

public class LoginRequestFixture {

    public static LoginRequest wrongEmail(){
        return new LoginRequest("toki0327@", "1234");
    }

    public static LoginRequest wrongPassword(){
        return new LoginRequest("toki0327@naver.com", "9999");
    }

    public static LoginRequest validRequest(){
        return new LoginRequest("toki0327@naver.com", "1234");
    }

}
