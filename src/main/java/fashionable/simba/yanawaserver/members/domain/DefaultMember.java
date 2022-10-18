package fashionable.simba.yanawaserver.members.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("default")
public class DefaultMember extends Member {
    protected DefaultMember() {/*no-op*/}

    public DefaultMember(String nickname, Long id, String email, List<String> roles, boolean isFirst) {
        super(nickname, id, email, roles, isFirst);
    }

    public DefaultMember(String nickname, String email, List<String> roles, boolean isFirst) {
        super(nickname, null, email, roles, isFirst);
    }
}
