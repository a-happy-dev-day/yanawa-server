package fashionable.simba.yanawaserver.auth.userdetails;

import java.util.List;

public interface UserDetails {
    Object getUsername();

    List<String> getAuthorities();
}
