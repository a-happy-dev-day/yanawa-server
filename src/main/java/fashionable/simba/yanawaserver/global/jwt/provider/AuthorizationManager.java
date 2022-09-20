package fashionable.simba.yanawaserver.global.jwt.provider;

import fashionable.simba.yanawaserver.global.context.Authentication;

public interface AuthorizationManager {
    Authentication authenticate(AuthenticationToken authenticationToken);
}
