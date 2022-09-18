package fashionable.simba.yanawaserver.members.service;

import fashionable.simba.yanawaserver.auth.dto.KakaoAccessToken;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.MemberRepository;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {
    private static final KakaoMember KAKAO_MEMBER = new KakaoMember(1234L, "kakao@email.com", List.of(RoleType.ROLE_MEMBER.name()), 1234L, "nickname", "image.jpg", "image.png");

    @Mock
    private MemberRepository memberRepository;
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userDetailsService = new CustomUserDetailsService(memberRepository);
    }

    @Test
    @DisplayName("username을 이용해 사용자를 찾는 경우에 사용자가 존재하지 않으면 예외가 발생한다.")
    void loadUserByUsername_noMember() {
        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(
            () -> userDetailsService.loadUserByUsername("1")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("사용자가 저장되어 있지 않다면 데이터베이스에 저장한다.")
    void saveKakaoMember_isEmpty() {
        when(memberRepository.findByKakaoId(any())).thenReturn(Optional.empty());
        when(memberRepository.save(any())).thenReturn(KAKAO_MEMBER);
        UserDetails 결과 = userDetailsService.saveKakaoMember(KAKAO_MEMBER);
        assertThat(결과.getUsername()).isEqualTo("1234");
    }

    @Test
    @DisplayName("저장되어 있다면 데이터베이스에 저장하지 않는다.")
    void saveKakaoMember_isNotEmpty() {
        when(memberRepository.findByKakaoId(any())).thenReturn(Optional.of(KAKAO_MEMBER));
        UserDetails 결과 = userDetailsService.saveKakaoMember(KAKAO_MEMBER);
        assertThat(결과.getUsername()).isEqualTo("1234");
    }
}
