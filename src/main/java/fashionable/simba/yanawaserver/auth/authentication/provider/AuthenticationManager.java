package fashionable.simba.yanawaserver.auth.authentication.provider;

import fashionable.simba.yanawaserver.auth.authentication.AuthenticationToken;
import fashionable.simba.yanawaserver.auth.context.Authentication;

public interface AuthenticationManager {
    Authentication authenticate(AuthenticationToken authenticationToken);
}
