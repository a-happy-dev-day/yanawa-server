package fashionable.simba.yanawaserver.global.jwt.provider;

public enum AuthorizationType {
    BEARER;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
