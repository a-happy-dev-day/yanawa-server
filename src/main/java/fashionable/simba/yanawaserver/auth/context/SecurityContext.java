package fashionable.simba.yanawaserver.auth.context;

import java.io.Serializable;

public class SecurityContext implements Serializable {
    private Authentication authentication;

    public SecurityContext() {
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
