package fashionable.simba.yanawaserver.auth.provider;

import java.util.Objects;

public final class AuthorizationToken {
    private final String principal;

    public AuthorizationToken(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorizationToken that = (AuthorizationToken) o;
        return Objects.equals(getPrincipal(), that.getPrincipal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrincipal());
    }
}
