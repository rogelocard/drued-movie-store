package com.ssc.druedmoviestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * This class models the movie object and its properties to be consulted and used for other classes. 
 * @author rosh
 *
 */
@Entity
@Data
@Table(name = "movies")
public class Movie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	private String genre; 
	private String director;
	private int inStock;
	private double price;
	private String description; // Varying length max allowed (255)
}
