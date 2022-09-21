package fashionable.simba.yanawaserver.global.provider;

import java.util.Objects;

public final class AuthenticationToken {
    private final String principal;

    public AuthenticationToken(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationToken that = (AuthenticationToken) o;
        return Objects.equals(getPrincipal(), that.getPrincipal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrincipal());
    }
}
