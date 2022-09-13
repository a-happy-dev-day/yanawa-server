package fashionable.simba.yanawaserver.members.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

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
        long kakaoId = 1234L;

        KakaoMember kakaoMember = assertDoesNotThrow(() -> new KakaoMember(
            kakaoId,
            "tis",
            "email@email.com",
            "profile",
            "thumnail",
            ACCESS_TOKEN)
        );

        assertAll(
            () -> assertThat(kakaoMember.getKakaoAccessToken()).isEqualTo(ACCESS_TOKEN),
            () -> assertThat(kakaoMember.getKakaoId()).isEqualTo(kakaoId)
        );
    }

    @Test
    @DisplayName("동등성 테스트")
    void kakaoMember_equals() {
        EqualsVerifier.forClass(KakaoMember.class)
            .suppress(Warning.NONFINAL_FIELDS)
            .withRedefinedSuperclass()
            .verify();
    }
}
