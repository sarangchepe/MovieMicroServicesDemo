package com.microservices.movies.moviecatalotservice.resources;

import com.microservices.movies.moviecatalotservice.models.CatalogItem;
import com.microservices.movies.moviecatalotservice.models.Movie;
import com.microservices.movies.moviecatalotservice.models.Rating;
import com.microservices.movies.moviecatalotservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog (@PathVariable("userId") String userId){

		// we should be making call to other microservice API but for now we are hardcoding it
		/*List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("1212", 3),
				new Rating("1021", 5)
		);*/

		UserRating userRating = restTemplate.getForObject(
				"http://localhost:8083/ratingsdata/userRating/"+ userId,
				UserRating.class);


		//for each rating, get the catalog item and return it.
		// we can use stream or forloop as well

		/*
		return ratings.stream().map(rating -> {
			new CatalogItem("movie1", "test movie Desc", rating.getRating());
		}).collect(Collectors.toList()) ;
		*/


		return userRating.getRatingList().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://localhost:8085/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getMovieName(), "testMovie Description", rating.getRating());
		})
				.collect(Collectors.toList());


		/*
		List<CatalogItem> catalogItems = new ArrayList<>();
		for (Rating r:ratings){
			Movie movie = restTemplate.getForObject("http://localhost:8085/movies/" + r.getMovieId(), Movie.class);
			catalogItems.add(new CatalogItem(movie.getMovieName(), "hardCode description", r.getRating()));
		}
		return catalogItems;
		*/

	}
}
