package fashionable.simba.yanawaserver.auth.kakao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class KakaoMemberTest {

    private static final KakaoAccessToken ACCESS_TOKEN = new KakaoAccessToken(
        "bearer",
        "access-token",
        new Date(),
        "refresh Token",
        new Date()
    );


    @Test
    @DisplayName("카카오 사용자의 정보를 생성한다.")
    void kakaoMember_create() {
        assertDoesNotThrow(
            () -> new KakaoMember(
                2L,
                "tis",
                "email@email.com",
                "profile",
                "thumnail",
                ACCESS_TOKEN
            )
        );
    }
}
