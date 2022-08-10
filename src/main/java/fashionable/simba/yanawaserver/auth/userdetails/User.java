package fashionable.simba.yanawaserver.auth.userdetails;

import java.util.List;

public class User implements UserDetails {
    private String username;
    private List<String> authorities;

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
