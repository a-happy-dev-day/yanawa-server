package fashionable.simba.yanawaserver.global.context;

import java.io.Serializable;

public class SecurityContext implements Serializable {
    private transient Authentication authentication;

    public SecurityContext() {
        this.authentication = null;
    }

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return authentication;
    }
}
