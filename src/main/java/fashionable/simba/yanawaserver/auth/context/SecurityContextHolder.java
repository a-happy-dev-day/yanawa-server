package fashionable.simba.yanawaserver.auth.context;


public class SecurityContextHolder {
    private static final ThreadLocal<SecurityContext> contextHolder;

    static {
        contextHolder = new ThreadLocal<>();
    }

    public static void clearContext() {
        contextHolder.remove();
    }

    public static SecurityContext getContext() {
        SecurityContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    public static void setContext(SecurityContext context) {
        if (context != null) {
            contextHolder.set(context);
        }
    }

    static SecurityContext createEmptyContext() {
        return new SecurityContext();
    }
}
