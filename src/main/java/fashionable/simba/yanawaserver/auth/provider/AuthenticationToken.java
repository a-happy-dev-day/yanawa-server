package fashionable.simba.yanawaserver.auth.provider;

public class AuthenticationToken {
    private final String principal;

    public AuthenticationToken(String principal) {
        this.principal = principal;
    }

    public String getPrincipal() {
        return principal;
    }

}
