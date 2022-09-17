package fashionable.simba.yanawaserver.members.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("default")
public class DefaultMember extends Member {
    protected DefaultMember() {/*no-op*/}

    public DefaultMember(Long id, String email, List<String> roles) {
        super(id, email, roles, null);
    }

    public DefaultMember(String email, List<String> roles) {
        super(null, email, roles, null);
    }
}
