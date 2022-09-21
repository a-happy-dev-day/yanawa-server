package fashionable.simba.yanawaserver.global.context;

import fashionable.simba.yanawaserver.members.domain.RoleType;

import java.util.List;

public class Authentication {
    public static final Authentication ANONYMOUS = new Authentication("anonymous", List.of(RoleType.ROLE_ANONYMOUS.name()));
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
