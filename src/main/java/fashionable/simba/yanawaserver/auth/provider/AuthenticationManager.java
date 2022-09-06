package fashionable.simba.yanawaserver.auth.provider;

import fashionable.simba.yanawaserver.auth.context.Authentication;

public interface AuthenticationManager {
    Authentication authenticate(AuthenticationToken authenticationToken);
}
