package fashionable.simba.yanawaserver.auth.provider;

public enum AuthorizationType {
    BEARER;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
