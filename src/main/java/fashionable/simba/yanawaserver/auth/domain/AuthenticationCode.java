package fashionable.simba.yanawaserver.auth.domain;

import java.util.Objects;

public final class AuthenticationCode {
    private String accessCode;

    private AuthenticationCode() {/*no-op*/}

    public AuthenticationCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return accessCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationCode that = (AuthenticationCode) o;
        return Objects.equals(getAccessCode(), that.getAccessCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccessCode());
    }
}
