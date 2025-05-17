package org.launchcode.BingeBuddy.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.apache.logging.log4j.util.InternalApi;


@Entity
public class Movie extends AbstractEntity {


    @Column
    @JsonProperty("Title")
    private String title;

    private int rating;

    @JsonProperty("Type")
    private String type;

    @Column(nullable = true)
    @JsonProperty("Year")
    private String year;


    @Column(nullable = true)
    @JsonProperty("Poster")
    private String poster;

    @InternalApi
    @Column(nullable = true, unique = true)
    @JsonProperty("imdbID")
    private String imdbId;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();



    public Movie() {

    }

    public Movie(String title, int rating, String type, String imdbId, String releaseDate, String description, String posterUrl) {
        this.title = title;
        this.rating = rating;
        this.type = type;
        this.year = releaseDate;
        this.poster = posterUrl;
        this.imdbId = imdbId;

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String releaseYear) {
        this.year = releaseYear;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> review) {
        this.reviews = review;
    }


}