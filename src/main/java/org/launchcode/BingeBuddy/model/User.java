package org.launchcode.BingeBuddy.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "user")
public class User extends AbstractEntity {
    private String username;

    //@JsonIgnore
    private String password;

    private String firstName;
    private String lastName;
    private String genre;
    private String anotherGenre;
//    private String email;

    private String role;

//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public void setEmail(String email) {
//        this.email = email;
//    }


    public User() {
    }

    public User(String username, String firstName, String lastName, String genre, String anotherGenre, String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genre = genre;
        this.anotherGenre = anotherGenre;
        this.role = role;
    }

    public User(Integer userId) {
        super();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAnotherGenre() {
        return anotherGenre;
    }

    public void setAnotherGenre(String anotherGenre) {
        this.anotherGenre = anotherGenre;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}