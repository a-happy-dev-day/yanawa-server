package fashionable.simba.yanawaserver.auth.kakao;

import fashionable.simba.yanawaserver.auth.filter.AccessCode;
import fashionable.simba.yanawaserver.auth.filter.AccessToken;
import fashionable.simba.yanawaserver.auth.filter.UserInfo;
import fashionable.simba.yanawaserver.members.domain.KakaoAccessToken;
import fashionable.simba.yanawaserver.members.domain.KakaoMember;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class KakaoAuthenticationService {
    private final KakaoAuthenticationClient authenticationClient;
    private final KakaoAuthorizationClient authorizationClient;
    private static final String GRANT_TYPE = "authorization_code";
    private final String redirectUri;
    private final String clientId;
    private final String secretKey;

    public KakaoAuthenticationService(KakaoAuthenticationClient authenticationClient,
                                      KakaoAuthorizationClient authorizationClient,
                                      @Value("${kakao.client.redirect-uri}") String redirectUri,
                                      @Value("${kakao.client.id}") String clientId,
                                      @Value("${kakao.client.secret}") String secretKey) {
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
    public AccessToken getAccessToken(AccessCode code) {
        return authenticationClient.getToken(
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            GRANT_TYPE,
            clientId,
            redirectUri,
            code.getAccessCode(),
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
        UserInfo userInfo = authorizationClient.getUserInfo(
            MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            "Bearer" + " " + token.getAccessToken()
        ).getBody();

        //TODO : 토큰을 형변환을 실행 시점에서 진행한다면 런타임에서 문제가 발생할 가능성이 있다. 제네릭을 사용은 어떨까?
        return new KakaoMember(
            Objects.requireNonNull(userInfo).getId(),
            userInfo.getEmail(),
            userInfo.getNickname(),
            userInfo.getProfileImage(),
            userInfo.getThumbnailImage(),
            token
        );
    }

    public AccessToken refreshToken(AccessToken token) {
        return null;
    }

    public void logout(AccessToken token) {

    }


}
