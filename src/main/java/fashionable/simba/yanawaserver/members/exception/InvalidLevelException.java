package fashionable.simba.yanawaserver.members.exception;

public class InvalidLevelException extends RuntimeException {
    private static final String message = "유효하지 않은 레벨입니다.";
    public InvalidLevelException() {
        super(message);
    }
}
