package fashionable.simba.yanawaserver.global.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawaserver.global.context.Authentication;
import fashionable.simba.yanawaserver.global.domain.AuthorizationToken;
import fashionable.simba.yanawaserver.global.provider.JwtTokenProvider;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public final ObjectMapper mapper;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenAuthenticationSuccessHandler(ObjectMapper mapper, JwtTokenProvider jwtTokenProvider) {
        this.mapper = mapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        AuthorizationToken tokenResponse = new AuthorizationToken(
            jwtTokenProvider.createAuthorizationToken(authentication.getPrincipal().toString(), authentication.getAuthorities()),
            jwtTokenProvider.createRefreshToken(authentication.getPrincipal().toString())
        );

        String responseToClient = mapper.writeValueAsString(tokenResponse);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().print(responseToClient);
    }
}
