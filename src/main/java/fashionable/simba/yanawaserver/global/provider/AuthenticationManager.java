package fashionable.simba.yanawaserver.global.provider;

import fashionable.simba.yanawaserver.global.context.Authentication;

public interface AuthenticationManager {
    Authentication authenticate(AuthenticationToken authenticationToken);
}
