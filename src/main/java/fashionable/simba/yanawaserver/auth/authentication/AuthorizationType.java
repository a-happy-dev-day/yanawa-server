package fashionable.simba.yanawaserver.auth.authentication;

public enum AuthorizationType {
    BEARER;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
