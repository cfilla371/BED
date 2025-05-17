package org.launchcode.BingeBuddy.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Watchlist extends AbstractEntity {


    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @Enumerated(EnumType.STRING) // Ensures the enum is stored as a string in the database
    @Column(nullable = false)
    private WatchlistStatus status;

    @Column(nullable = true)
    private LocalDate scheduledDate;


    public Watchlist() {
    }

    public Watchlist(Movie movie, WatchlistStatus status) {

        this.movie = movie;
        this.status = status;
    }


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WatchlistStatus getStatus() {
        return status;
    }

    public void setStatus(WatchlistStatus status) {
        this.status = status;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }


}
