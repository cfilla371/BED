package org.launchcode.BingeBuddy.security;


import org.launchcode.BingeBuddy.data.UserEntityRepository;
import org.launchcode.BingeBuddy.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserEntityRepository userEntityRepository;

    public MyUserDetailsService(UserEntityRepository userAuthRepository){
        this.userEntityRepository =userAuthRepository;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userEntityRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("This user does not exist in the database");
        }

        return new UserAuthSecurity(user);
    }

}
