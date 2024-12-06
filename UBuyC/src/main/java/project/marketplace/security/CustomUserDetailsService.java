package project.marketplace.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.marketplace.daos.AccountDao;

import java.util.Collections;

/**
 * This service sets user details
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountDao accountDao;

    /**
     * Loads user details from email
     *
     * @param email the email of the user
     * @return user login details
     * @throws UsernameNotFoundException if the email isn't connected to an account
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService.java: Attempting to load user: " + email);
        project.marketplace.models.User user = accountDao.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("CustomUserDetailsService.java: User not found");
        }

        return new User(
                user.getEmail(),
                user.getPasswordHash(),
                Collections.singletonList(new org.springframework.security.core.authority.
                        SimpleGrantedAuthority("USER"))
        );
    }
}
