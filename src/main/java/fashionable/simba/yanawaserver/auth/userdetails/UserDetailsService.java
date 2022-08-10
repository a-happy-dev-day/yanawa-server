package fashionable.simba.yanawaserver.auth.userdetails;

public interface UserDetailsService {
    UserDetails loadUserByUsername(String principal);
}
