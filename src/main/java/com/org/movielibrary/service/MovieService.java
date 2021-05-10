package com.org.movielibrary.service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.org.movielibrary.model.Movie;

@Service
public class MovieService {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

	
	private String fileName="data/movies.json";
	
	public List<Movie>  getAllMovies() throws IOException {
		
		Gson gson = new Gson();
		File resource = new ClassPathResource(
				this.fileName).getFile();
		List<Movie> data = gson.fromJson(new FileReader(resource),  List.class);
		
		String path = resource.getAbsolutePath();
		
		logger.info("\n[Path] : " + path);
		
		return data;	
		
	}
	
	public Movie getMovie(int index ) throws IOException {
		
		Gson gson = new GsonBuilder().create();
		File resource = new ClassPathResource(
				this.fileName).getFile();
		Movie[] data = gson.fromJson(new FileReader(resource),  Movie[].class);
		
		return data[index];		
	}
	
	public void deleteMovie(int index ) throws IOException {
		
		Gson gson = new GsonBuilder().create();
		File resource = new ClassPathResource(
				this.fileName).getFile();
		List<Movie> data = gson.fromJson(new FileReader(resource),  List.class);		
		
		data.remove(index);
		FileWriter fw = new FileWriter(resource);
        
		gson.toJson(data, fw);
        fw.close();
	}
	
	   public Movie updateMovie( int index , Movie movie) throws IOException, ParseException {
			
			
			Gson gson = new GsonBuilder().create();
			File resource = new ClassPathResource(
					this.fileName).getFile();
			List<Movie> data = gson.fromJson(new FileReader(resource),  List.class);		
			
			data.set(index, movie);
			FileWriter fw = new FileWriter(resource);
	        
			gson.toJson(data, fw);
	        fw.close();
			return movie;
			
		}
	   
	  public  List<Movie> searchMovie( Movie movie) throws IOException {		
			Gson gson = new GsonBuilder().create();
			File resource = new ClassPathResource(
					this.fileName).getFile();
			Movie[] data = gson.fromJson(new FileReader(resource),  Movie[].class);		
			List<Movie> dataNew = new ArrayList<Movie>();
			for(Movie m : data)
			{
				if(m.equals(movie))
				{
					dataNew.add(m);
				}
			}
			return dataNew;
			
		}
	  
	  public Movie newMovie( Movie movie) throws IOException {
			
			
			Gson gson = new GsonBuilder().create();
			File resource = new ClassPathResource(
					this.fileName).getFile();
			List<Movie> data = gson.fromJson(new FileReader(resource),  List.class);		
			
			data.add(movie);
			//gson.toJson(data, new FileWriter(jsonOutput));
			FileWriter fw = new FileWriter(resource);
	        
			gson.toJson(data, fw);
			fw.flush();
	        fw.close();
			return movie;
			
		}

}
