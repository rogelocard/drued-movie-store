package com.ssc.druedmoviestore.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssc.druedmoviestore.exception.ResourceNotFoundException;
import com.ssc.druedmoviestore.model.User;
import com.ssc.druedmoviestore.repository.IUserRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * This method handles the business logic and uses the user repository to  
 * provide a service that can be called from other classes.
 * @author Rosh
 */
@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	/**
	 * The Entity Manager class allows us to use SQl queries when needed
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	// Method to post/save a new user. 
	public User addUser (User user) {
		 return userRepository.save(user);
	};
	
	// Method to list all movies. 
	public List<User> listAllUsers(){
		return userRepository.findAll();
	}
	
	// Method to get a user by Id. 
	public Optional<User>  findById (Long id) {
		 return userRepository.findById(id);
	};
	
	// Methodo to delete a user by ID.
	public void deleteMovie (Long id) {
		userRepository.deleteById(id);
	};

	// PUT/ Method to update a movie.
	public User editUser(@PathVariable Long id, @RequestBody User userDetails){
		User user = userRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException(("There isn't movie with the ID: " + id)));
		
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setUsername(userDetails.getUsername());
		user.setPassword(userDetails.getPassword());
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	/**
	 * Method to validate if the credentials entered are the same on the database
	 * @param user
	 * @return
	 */
	public boolean verifyCredentials(User user) {
		String query = "FROM User WHERE username = :username";
		List<User> list = entityManager.createQuery(query)
					.setParameter("username", user.getUsername())
					.getResultList();
		
		if (list.isEmpty()) {
			return false;
		}
		
		String passwordHashed = list.get(0).getPassword();
		
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
		// Verify() = Method to compare the password hash we have store in the database with the one we get in the front as a request. 
		return argon2.verify(passwordHashed, user.getPassword()); 
		
	}
}
