package fashionable.simba.yanawaserver.global.provider;

public enum AuthorizationType {
    BEARER;

    public String toLowerCase() {
        return this.name().toLowerCase();
    }
}
