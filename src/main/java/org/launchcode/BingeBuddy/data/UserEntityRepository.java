package org.launchcode.BingeBuddy.data;

import org.launchcode.BingeBuddy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<User, Integer> {

    //Optional<User> findByEmail(String email);

    User findByUsername(String username);

    // Gives a WebSecurity Exception
    @Query("SELECT u FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))")
    List<User> findByUsernameContaining(@Param("username") String username);


}
