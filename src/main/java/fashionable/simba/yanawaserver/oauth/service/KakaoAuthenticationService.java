package fashionable.simba.yanawaserver.oauth.service;

import fashionable.simba.yanawaserver.oauth.domain.UserInfo;
import fashionable.simba.yanawaserver.oauth.infra.KakaoAuthenticationClient;
import fashionable.simba.yanawaserver.oauth.infra.KakaoAuthorizationClient;
import fashionable.simba.yanawaserver.oauth.infra.dto.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.util.Objects;

public class KakaoAuthenticationService {
    private final Logger log = LoggerFactory.getLogger(KakaoAuthenticationService.class);
    private final KakaoAuthenticationClient authenticationClient;
    private final KakaoAuthorizationClient authorizationClient;
    private static final String GRANT_TYPE = "authorization_code";
    private static final String CODE = "code";
    private final String redirectUri;
    private final String clientId;
    private final String secretKey;

    public KakaoAuthenticationService(KakaoAuthenticationClient authenticationClient,
                                      KakaoAuthorizationClient authorizationClient,
                                      String redirectUri,
                                      String clientId,
                                      String secretKey) {
        this.authenticationClient = authenticationClient;
        this.authorizationClient = authorizationClient;
        this.redirectUri = redirectUri;
        this.clientId = clientId;
        this.secretKey = secretKey;
    }

    /**
     * Kakao API에서 AccessCode를 입력해 AccessToken을 반환받는다.
     * GRANT_TYPE은 authorization_code으로 고정한다.
     * Client id, Redirect uri, Secret key는 AuthConfig.java에서 값을 입력받는다.
     *
     * @param code
     * @return AccessToken
     */
    public KakaoAccessToken getAccessToken(String code) {
        log.info("Request Access Token to kakao, code is {}", code);

        return authenticationClient.getToken(
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            GRANT_TYPE,
            clientId,
            redirectUri,
            code,
            secretKey).getBody();
    }

    /**
     * AccessToken을 입력해 UserInfo를 반환받는다.
     * KakaoUserinfo에는 많은 정보가 포함되어 있고, 그 중 필요한 정보를 UserInfo에 선언한다.
     * 마지막으로 사용자의 토큰 정보를 계속해서 갱신해주기 위해 AccessToken도 저장한다.
     *
     * @param token
     * @return KakaoMember
     */
    public KakaoMember getUserInfo(KakaoAccessToken token) {
        final String accessToken = token.getAccessToken();

        log.info("Request user information in kakao, token is {}", accessToken);
        UserInfo userInfo = authorizationClient.getUserInfo(
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            "Bearer" + " " + accessToken
        ).getBody();

        final Long kakaoId = Objects.requireNonNull(userInfo).getId();

        log.info("Get User information user id is {}", kakaoId);

        return new KakaoMember(
            kakaoId,
            userInfo.getEmail(),
            userInfo.getNickname(),
            userInfo.getProfileImage(),
            userInfo.getThumbnailImage(),
            true
        );
    }

    public String getLoginUri() {
        return "https://kauth.kakao.com/oauth/authorize?" +
            "client_id=" + clientId +
            "&redirect_uri=" + redirectUri +
            "&response_type=" + CODE;
    }
}
