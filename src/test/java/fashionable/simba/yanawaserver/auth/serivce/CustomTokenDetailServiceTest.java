package fashionable.simba.yanawaserver.auth.serivce;

import fashionable.simba.yanawaserver.global.token.domain.TokenManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class CustomTokenDetailServiceTest {

    @InjectMocks
    CustomTokenDetailService customTokenDetailService;

    @Mock
    TokenManager tokenManager;

    @Test
    @DisplayName("invalidAccessTokenRepository에 존재하는 토큰인지 확인한다.")
    void validateAccessToken() {
        // given
        String accessToken = "invalid access token";

        // when
        doNothing().when(tokenManager).verifyAccessToken(accessToken);

        // then
        customTokenDetailService.validateAccessToken(accessToken);
    }
}
