package com.org.movielibrary.controller;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.movielibrary.model.Movie;
import com.org.movielibrary.service.MovieService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class MovieController {
	
	@Autowired
    private MovieService movieService;
	
	@PostMapping("/movie/add")
    Movie newMovie( @RequestBody Movie movie) throws IOException {
		
		
		return movieService.newMovie(movie);
		
	}
	
	@PostMapping("/movie/search")
	List<Movie> searchMovie( @RequestBody Movie movie) throws IOException {		
		
		return movieService.searchMovie(movie);
		
	}
	
	@RequestMapping(value = "/movie/update/{index}", method = RequestMethod.POST)
    Movie updateMovie(@PathVariable("index")  int index , @RequestBody Movie movie) throws IOException, ParseException {
		return movieService.updateMovie(index, movie);
		
	}
	
	@RequestMapping(value = "/movie/delete/{index}", method = RequestMethod.GET)
	void deleteMovie(@PathVariable("index") int index ) throws IOException {		
		movieService.deleteMovie(index);
	}
	
	@RequestMapping(value = "/movie/get/{index}", method = RequestMethod.GET)
	Movie getMovie(@PathVariable("index") int index ) throws IOException {		
		return movieService.getMovie(index);		
	}
	
	@GetMapping("/movie/list")
	List<Movie> all() throws IOException {		
		return movieService.getAllMovies();
	}

}
