package com.ssc.druedmoviestore.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssc.druedmoviestore.model.Movie;
import com.ssc.druedmoviestore.service.MovieService;

/**
 * Controller used to redirect a user's petition to a movie business logic
 * @author Rosh
 *
 */
@RestController
@RequestMapping ("api/peliculas")
public class MovieController {

	@Autowired
	private MovieService movieService;
	
	@PostMapping ("/guardarPelicula")
	public ResponseEntity<Movie> saveMovie (@RequestBody Movie movie){
		// Temporal movie = movie1
		Movie movie1 = movieService.addMovie(movie);
		
		try {
			return ResponseEntity.created(new URI("api/peliculas/guardarPelicula"+ movie1.getId())).body(movie1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} 
	}
	
	@GetMapping
	public ResponseEntity<List<Movie>> listAllMovies (){
		return ResponseEntity.ok(movieService.listAllMovies());
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	private ResponseEntity<Void> deleteMovie (@PathVariable Long id){
		movieService.deleteMovie(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Optional<Movie>> listMoviesById (@PathVariable ("id") Long id){
		return ResponseEntity.ok(movieService.findById(id));
	}
	
	@PutMapping("/pelicula/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails){
		movieService.findById(id);
		return ResponseEntity.ok(movieService.editMovie(id, movieDetails));
	}
	
}
