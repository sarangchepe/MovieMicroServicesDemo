package com.microservices.movies.moviecatalotservice.models;

import java.util.List;

public class UserRating {
    private List<Rating> ratingList;

    // Its important to create default constructor
    // else we won't be able to call other microservice API from this services
    public UserRating() {
    }

    public UserRating(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }
}
