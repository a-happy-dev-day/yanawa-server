package fashionable.simba.yanawaserver.members.service;

public class AlreadyUpdatedException extends RuntimeException {
    public AlreadyUpdatedException(String message) {
        super(message);
    }
}
