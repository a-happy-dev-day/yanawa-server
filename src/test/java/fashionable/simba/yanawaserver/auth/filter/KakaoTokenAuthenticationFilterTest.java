package fashionable.simba.yanawaserver.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.kakao.KakaoAuthenticationService;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.userdetails.User;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import fashionable.simba.yanawaserver.members.domain.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KakaoTokenAuthenticationFilterTest {
    private static final long KAKAO_ID = 1234L;
    private static final Date EXPIRES_IN = new Date();
    private static final Date REFRESH_TOKEN_EXPIRES_IN = new Date();
    private static final KakaoAccessToken KAKAO_ACCESS_TOKEN = new KakaoAccessToken("Bearer", "accessToken", EXPIRES_IN, "refreshToken", REFRESH_TOKEN_EXPIRES_IN);
    private static final KakaoMember KAKAO_MEMBER = new KakaoMember(KAKAO_ID, "kakao@email.com", "nickname", "profile_image.jpg", "thumbnail.jpg", KAKAO_ACCESS_TOKEN);
    private static final User USER = new User(String.valueOf(KAKAO_ID), List.of(RoleType.ROLE_MEMBER.name()));
    @Mock
    private AuthenticationSuccessHandler successHandler;
    @Mock
    private AuthenticationFailureHandler failureHandler;
    @Mock
    private ObjectMapper mapper;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private KakaoAuthenticationService kakaoAuthenticationService;
    @Mock
    private UserDetailsService userDetailsService;

    @Autowired
    private KakaoTokenAuthenticationFilter filter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        filter = new KakaoTokenAuthenticationFilter(successHandler, failureHandler, authenticationManager, mapper, kakaoAuthenticationService, userDetailsService);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    /**
     * 사용자가 소셜 로그인을 진행해 토큰을 받는다.
     * 토큰을 이용해 사용자의 정보를 조회한다.
     * 카카오 ID를 이용해 사용자 정보를 확인한다.
     * 이메일을 이용해 사용자 정보를 확인한다.
     * 카카오에 접근할 수 있는 Bearer 타입의 Access 토큰과 Refresh 토큰과 유효시간을 데이터베이스에 저장한다.
     */
    @Test
    @DisplayName("사용자가 소셜 로그인을 진행해 토큰을 발고, 사용자의 정보를 조회한다.")
    void getTokenByKakao() throws IOException {
        when(kakaoAuthenticationService.getAccessToken(any())).thenReturn(KAKAO_ACCESS_TOKEN);
        when(kakaoAuthenticationService.getUserInfo(any())).thenReturn(KAKAO_MEMBER);
        when(userDetailsService.saveKakaoMember(any())).thenReturn(USER);
        AuthenticationToken authenticationToken = filter.convert(request);
        assertThat(authenticationToken).isEqualTo(new AuthenticationToken(String.valueOf(KAKAO_ID)));
    }

    @Test
    @DisplayName("헤더에 토큰을 추가해 응답한다.")
    void responseToken() throws IOException {
        Authentication authentication = new Authentication("1234", List.of(RoleType.ROLE_MEMBER.name()));
        when(kakaoAuthenticationService.getAccessToken(any())).thenReturn(KAKAO_ACCESS_TOKEN);
        when(kakaoAuthenticationService.getUserInfo(any())).thenReturn(KAKAO_MEMBER);
        when(userDetailsService.saveKakaoMember(any())).thenReturn(USER);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        doNothing().when(successHandler).onAuthenticationSuccess(request, response, authentication);

        filter.preHandle(request, response, new Object());
    }
}
