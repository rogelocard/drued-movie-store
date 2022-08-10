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

import org.springframework.web.bind.annotation.RestController;

import com.ssc.druedmoviestore.model.User;
import com.ssc.druedmoviestore.service.UserService;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Controller used to redirect a user's petition to a user business logic
 * @author Rosh
 *
 */
@RestController
@RequestMapping("api/usuarios")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Function to create an API that will allow to post an object User through the URL
	@PostMapping ("/guardar")
	public ResponseEntity<User> saveUser (@RequestBody User user){
		//Instructions to hash the password and encrypt it in the database. 
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		String hash = argon2.hash(1, 1024, 1, user.getPassword());
		user.setPassword(hash);

		// Temporal user = user1
		User user1 = userService.addUser(user);
		
		try {
			return ResponseEntity.created(new URI("api/usuarios/guardar"+ user1.getId())).body(user1);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} 
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAllUsers (){
		return ResponseEntity.ok(userService.listAllUsers());
	}
	
	@DeleteMapping(value = "/eliminar/{id}")
	private ResponseEntity<Void> deleteUser (@PathVariable Long id){
		userService.deleteMovie(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Optional<User>> listMoviesById (@PathVariable ("id") Long id){
		return ResponseEntity.ok(userService.findById(id));
	}
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<User> updateMovie(@PathVariable Long id, @RequestBody User userDetails){
		userService.findById(id);
		return ResponseEntity.ok(userService.editUser(id, userDetails));
	}
	
}
