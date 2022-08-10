package fashionable.simba.yanawaserver.auth.authentication.provider;

import fashionable.simba.yanawaserver.auth.context.Authentication;
import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;

public interface AuthenticationManager {
    Authentication authenticate(AuthenticationToken authenticationToken);
}
