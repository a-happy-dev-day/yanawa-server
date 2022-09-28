package fashionable.simba.yanawaserver.global.context;


public class SecurityContextHolder {
    private static final ThreadLocal<SecurityContext> contextHolder;

    static {
        contextHolder = new ThreadLocal<>();
    }

    private SecurityContextHolder() {
        throw new IllegalStateException("SecurityContextHolder는 유틸 클래스입니다.");
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
        if (context == null) {
            throw new IllegalStateException("context가 null일 수 없습니다.");
        }
        contextHolder.set(context);
    }

    static SecurityContext createEmptyContext() {
        return new SecurityContext();
    }
}
