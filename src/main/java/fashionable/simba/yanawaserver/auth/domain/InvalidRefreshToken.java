package fashionable.simba.yanawaserver.auth.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InvalidRefreshToken {
    @Id
    private String refreshToken;
    private Date date;

    protected InvalidRefreshToken() {/*no-op*/}

    public InvalidRefreshToken(String refreshToken, Date date) {
        this.refreshToken = refreshToken;
        this.date = date;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Date getDate() {
        return date;
    }
}
