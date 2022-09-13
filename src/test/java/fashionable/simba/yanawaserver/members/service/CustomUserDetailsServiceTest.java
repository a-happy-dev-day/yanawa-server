package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.members.domain.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.KakaoMemberRepository;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    private static final KakaoAccessToken KAKAO_ACCESS_TOKEN = new KakaoAccessToken("bearer", "access token", new Date(), "refresh toekn", new Date());
    private static final KakaoMember KAKAO_MEMBER = new KakaoMember(1234L, "kakao@email.com", "nickname", "image.jpg", "image.png", KAKAO_ACCESS_TOKEN);
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private KakaoMemberRepository kakaoMemberRepository;
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new CustomUserDetailsService(memberRepository, kakaoMemberRepository);
    }

    @Test
    @DisplayName("사용자가 저장되어 있지 않다면 데이터베이스에 저장한다.")
    void saveKakaoMember_isEmpty() {
        when(kakaoMemberRepository.findByKakaoId(any())).thenReturn(Optional.empty());
        when(kakaoMemberRepository.save(any())).thenReturn(KAKAO_MEMBER);
        userDetailsService.saveKakaoMember(KAKAO_MEMBER);
    }

    @Test
    @DisplayName("저장되어 있다면 데이터베이스에 저장하지 않는다.")
    void saveKakaoMember_isNotEmpty() {
        when(kakaoMemberRepository.findByKakaoId(any())).thenReturn(Optional.of(KAKAO_MEMBER));
        userDetailsService.saveKakaoMember(KAKAO_MEMBER);
    }
}
