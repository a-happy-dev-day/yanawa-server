package fashionable.simba.yanawaserver.global.userdetails;

import java.util.List;

public class User implements UserDetails {
    private final String username;
    private final List<String> authorities;

    public User(String username, List<String> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public List<String> getAuthorities() {
        return authorities;
    }
}
