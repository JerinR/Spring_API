package com.example.api.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.entity.User;
import com.example.api.exception.BadRequestException;
import com.example.api.exception.NotFoundException;
import com.example.api.repository.UserRepository;
import com.example.api.service.UserService;

@Service
public class UserServiceImlp implements UserService{
	
	private UserRepository repository;
	public UserServiceImlp(UserRepository repository) {
		this.repository = repository;
	}
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User findOne(String id) {
		return repository.findById(id).orElseThrow(()->new NotFoundException("User with id "+ id +" does not exist"));
		/*User existing = repository.findOne(id);
		if(existing == null) {
			throw new NotFoundException("User with id "+ id +" does not exist");
		}
		return existing;*/
	}

	@Override
	@Transactional
	public User create(User user) {
		//repository.findByEmail(user.getEmail()).orElseThrow(()->new BadRequestException("User with email "+ user.getEmail() +" already exists"));

		Optional<User> existing = repository.findByEmail(user.getEmail());
		if(existing.isPresent()) {
			throw new BadRequestException("User with email "+ user.getEmail() +" already exists");
		}
		return repository.save(user);
	}

	@Override
	@Transactional
	public User update(String id, User user) {
		repository.findById(id).orElseThrow(()->new NotFoundException("User with id "+ id +" does not exist"));
		/*if(existing == null) {
			throw new NotFoundException("User with id "+ id +" does not exist");
		}*/
		return repository.save(user);
	}

	@Override
	@Transactional
	public void delete(String id) {
		User existing = repository.findById(id).orElseThrow(()->new NotFoundException("User with id "+ id +" does not exist"));
		
		repository.delete(existing);
	}
	
}
