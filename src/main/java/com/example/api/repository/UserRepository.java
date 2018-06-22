package com.example.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.example.api.entity.User;

// extends the entity as well as the data type of Id of entity
public interface UserRepository extends Repository<User, String>{
	
	public List<User> findAll();
	
	public Optional<User> findById(String id);
	
	public Optional<User> findByEmail(String email);// if you want to find by any a
													//	attribute, you can say findByAttributename
													// spring will take care of the rest
	
	public User save(User user); //create and update
	
	public void delete(User user);
}
