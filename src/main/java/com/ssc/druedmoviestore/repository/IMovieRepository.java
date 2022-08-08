package com.ssc.druedmoviestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssc.druedmoviestore.model.Movie;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long>{

}
