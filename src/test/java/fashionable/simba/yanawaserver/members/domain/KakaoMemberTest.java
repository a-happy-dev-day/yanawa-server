package fashionable.simba.yanawaserver.members.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class KakaoMemberTest {

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
            true)
        );

        assertAll(
            () -> assertThat(kakaoMember.getKakaoId()).isEqualTo(kakaoId)
        );
    }
}
