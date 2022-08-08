package com.ssc.druedmoviestore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssc.druedmoviestore.exception.ResourceNotFoundException;
import com.ssc.druedmoviestore.model.Movie;
import com.ssc.druedmoviestore.repository.IMovieRepository;

/**
 * This method handles the business logic and uses the movie repository to  
 * provide a service that can be called from other classes.
 * @author Rosh
 *
 */
@Service
public class MovieService {
	
	@Autowired
	private IMovieRepository movieRepository;
	
	// Method to post/save a new movie. 
	public Movie addMovie (Movie movie) {
		 return movieRepository.save(movie);
	};
	
	// Method to list all movies. 
	public List<Movie> listAllMovies(){
		return movieRepository.findAll();
	}
	
	// Method to get by Id. 
	public Optional<Movie>  findById (Long id) {
		 return movieRepository.findById(id);
	};
	
	// Methodo to delete by ID.
	public void deleteMovie (Long id) {
		movieRepository.deleteById(id);
	};
	
	// PUT/ Method to update a movie.
	public Movie editMovie(@PathVariable Long id, @RequestBody Movie movieDetails){
		Movie movie = movieRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException(("There isn't movie with the ID: " + id)));
		
		movie.setName(movieDetails.getName());
		movie.setGenre(movieDetails.getGenre());
		movie.setDirector(movieDetails.getDirector());
		movie.setInStock(movieDetails.getInStock());
		movie.setPrice(movieDetails.getPrice());
		movie.setDescription(movieDetails.getDescription());
		
		Movie updatedMovie = movieRepository.save(movie);
		return updatedMovie;
	}
	
	
}
