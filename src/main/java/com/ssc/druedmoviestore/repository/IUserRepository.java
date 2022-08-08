package com.ssc.druedmoviestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssc.druedmoviestore.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{
	
}
