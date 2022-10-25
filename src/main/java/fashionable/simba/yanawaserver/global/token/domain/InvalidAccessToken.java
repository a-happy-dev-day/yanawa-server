package fashionable.simba.yanawaserver.global.token.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InvalidAccessToken {
    @Id
    private String accessToken;
    private Date date;

    protected InvalidAccessToken() {
    }

    public InvalidAccessToken(String accessToken, Date date) {
        this.accessToken = accessToken;
        this.date = date;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public Date getDate() {
        return date;
    }
}
