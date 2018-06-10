package jp.zero.oauthserver.service;

import jp.zero.oauthserver.data.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        switch(username) {
            case "user01":
                return new User(1,"user01", "password01", "user01@test.com", 10);
            case "user02":
                return new User(2,"user02", "password02", "user02@test.com", 20);
            case "user03":
                return new User(3,"user03", "password03", "user03@test.com", 30);
            default:
                return new User(4,"default", "default", "default@test.com", 0);
        }
    }
}
