package fashionable.simba.yanawaserver.auth.context;

import java.util.List;

public class Authentication {
    private final Object principal;
    private List<String> authorities;

    public Authentication(Object principal, List<String> authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    public Object getPrincipal() {
        return principal;
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
