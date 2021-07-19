package com.microserviceDemo.movieratingDataservice.resources;

import com.microserviceDemo.movieratingDataservice.models.Movie;
import com.microserviceDemo.movieratingDataservice.models.Rating;
import com.microserviceDemo.movieratingDataservice.models.UserRating;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/userRating/{userId}")
    public UserRating getRatingsByUser(@PathVariable("userId") String userId){

        UserRating userRating = new UserRating();


        if (userId.equals("sarang")){
            userRating.setRatingList(Arrays.asList(
                    new Rating("m12",4),
                    new Rating("m11",3),
                    new Rating("m22", 5)
            ));
        }
        else{
            userRating.setRatingList(Arrays.asList(
                    new Rating("m55",5),
                    new Rating("m44",3),
                    new Rating("m33", 5)
            ));
        }
        return userRating;
    }
}
