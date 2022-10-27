package fashionable.simba.yanawaserver.global.filter.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import fashionable.simba.yanawaserver.global.token.domain.AuthorizationToken;
import fashionable.simba.yanawaserver.global.token.domain.TokenManager;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public final ObjectMapper mapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenManager tokenManager;

    public TokenAuthenticationSuccessHandler(ObjectMapper mapper, JwtTokenProvider jwtTokenProvider, TokenManager tokenManager) {
        this.mapper = mapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenManager = tokenManager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        AuthorizationToken tokenResponse = new AuthorizationToken(
            jwtTokenProvider.createAuthorizationToken(authentication.getPrincipal().toString(), authentication.getAuthorities()),
            jwtTokenProvider.createRefreshToken(authentication.getPrincipal().toString())
        );

        tokenManager.manageAccessToken(tokenResponse.getAccessToken());
        tokenManager.manageRefreshToken(tokenResponse.getRefreshToken());

        String responseToClient = mapper.writeValueAsString(tokenResponse);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().print(responseToClient);
    }
}
