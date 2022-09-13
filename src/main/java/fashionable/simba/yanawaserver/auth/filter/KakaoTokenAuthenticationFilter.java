package fashionable.simba.yanawaserver.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationFailureHandler;
import fashionable.simba.yanawaserver.auth.handler.AuthenticationSuccessHandler;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationManager;
import fashionable.simba.yanawaserver.auth.provider.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetails;
import fashionable.simba.yanawaserver.auth.userdetails.UserDetailsService;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

public class KakaoTokenAuthenticationFilter extends AbstractAuthenticationFilter {
    private final ObjectMapper mapper;
    private final AuthenticationService authenticationService;
    private final UserDetailsService userDetailsService;

    public KakaoTokenAuthenticationFilter(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, AuthenticationManager authenticationManager, ObjectMapper mapper, AuthenticationService authenticationService, UserDetailsService userDetailsService) {
        super(successHandler, failureHandler, authenticationManager);
        this.mapper = mapper;
        this.authenticationService = authenticationService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * 사용자가 소셜 로그인을 진행해 토큰을 발급받는다.*
     * 발급받은 토큰으로 사용자의 정보를 조회하고 사용자 정보가 없는 경우 사용자 정보를 저장한다.*
     * 카카오 ID를 이용해 사용자 정보를 확인한다.*
     * 이메일을 이용해 사용자 정보를 확인한다.*
     * 카카오에 접근할 수 있는 Bearer 타입의 Access 토큰과 Refresh 토큰과 유효시간을 데이터베이스에 저장한다.*
     * (이 때, 데이터베이스는 RDBMS가 맡지만 추후, 레디스가 적용될 수 있다는 점을 고려하자)*
     * 토큰을 생성한다. 토큰안에는 데이터베이스에 저장된 사용자의 ID와 유효시간 정보가 포함되어 있다.*
     * 유효시간은 카카오 API 토큰의 유효시간을 따른다.*
     * 헤더에 토큰을 추가해 응답한다.*
     *
     * @param request current HTTP request
     * @return AuthenticationToken
     * @throws IOException
     */
    @Override
    protected AuthenticationToken convert(HttpServletRequest request) throws IOException {
        String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        AccessCode code = mapper.readValue(content, AccessCode.class);

        AccessToken accessToken = authenticationService.getAccessToken(code);

        KakaoMember kakaoMember = authenticationService.getUserInfo(accessToken);

        UserDetails userDetails = userDetailsService.saveKakaoMember(kakaoMember);

        return new AuthenticationToken((String) userDetails.getUsername());
    }

    @Override
    protected boolean getContinueChainBeforeSuccessfulAuthentication() {
        return false;
    }

    @Override
    protected boolean getContinueChainBeforeUnsuccessfulAuthentication() {
        return false;
    }
}
