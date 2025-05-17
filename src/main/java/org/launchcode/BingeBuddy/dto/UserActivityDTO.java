package org.launchcode.BingeBuddy.dto;

public class UserActivityDTO {

    String reviews;
    String comments;
    String watchlists;

    public UserActivityDTO(){

    }

    public UserActivityDTO(String reviews, String comments, String watchlists) {
        this.reviews = reviews;
        this.comments = comments;
        this.watchlists = watchlists;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getWatchlists() {
        return watchlists;
    }

    public void setWatchlists(String watchlists) {
        this.watchlists = watchlists;
    }
}
