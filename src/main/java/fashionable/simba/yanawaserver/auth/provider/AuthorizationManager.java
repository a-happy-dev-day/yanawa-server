package fashionable.simba.yanawaserver.auth.provider;

import fashionable.simba.yanawaserver.auth.context.Authentication;

public interface AuthorizationManager {
    Authentication authenticate(AuthenticationToken authenticationToken);
}
