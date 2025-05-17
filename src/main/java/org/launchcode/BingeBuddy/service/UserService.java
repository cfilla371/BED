package org.launchcode.BingeBuddy.service;


import org.launchcode.BingeBuddy.data.UserEntityRepository;
import org.launchcode.BingeBuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired // added now
    private final UserEntityRepository userEntityRepository;

    public UserService(UserEntityRepository userEntityRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       BCryptPasswordEncoder bCryptPasswordEncoder1) {

        this.userEntityRepository = userEntityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder1;
    }

    public List<User> getUsers() {
        return userEntityRepository.findAll();
    }

    public User getUser(Integer id) {
        return userEntityRepository.findById(id).orElse(null);
    }

    public User addUser(User userAuth) {
        userAuth.setPassword(bCryptPasswordEncoder.encode(userAuth.getPassword()));
        return userEntityRepository.save(userAuth);
    }

    public User updateUser(User userAuth) {
        return userEntityRepository.save(userAuth);
    }

    public void deleteUser(Integer id) {
        userEntityRepository.deleteById(id);
        ;
    }

    public boolean authenticate(String username, String password) {
        User user = userEntityRepository.findByUsername(username);

        if (user == null) {
            throw new BadCredentialsException("User not found.");
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password.");
        }

        return true;
    }


}
