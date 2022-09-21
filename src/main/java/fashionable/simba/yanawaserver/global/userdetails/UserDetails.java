package fashionable.simba.yanawaserver.global.userdetails;

import java.util.List;

public interface UserDetails {
    Object getUsername();

    List<String> getAuthorities();
}
