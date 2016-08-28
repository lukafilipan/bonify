package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filipan.model.User;
import com.filipan.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Async
	@Override
	public CompletableFuture<Collection<User>> findAll(){
		Collection<User> users = userRepository.findAll();
		return CompletableFuture.completedFuture(users);
	}
	
	@Async
	@Override
	public CompletableFuture<User> findOne(Long id) {
		User user = userRepository.findOne(id);
		return CompletableFuture.completedFuture(user);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CompletableFuture<User> create(User user) {
		if (user.getId() != null) {
			// Cannot create user
			return null;
		}
		User savedUser = userRepository.save(user);
		return CompletableFuture.completedFuture(savedUser);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	//@CachePut(value = "user", key = "#user.id")
	public CompletableFuture<User> update(User user) {
		User userPersisted = userRepository.findOne(user.getId());
		if (userPersisted == null) {
			// Cannot update user
			return null;
		}
		User updatedUser = userRepository.save(user);
		return CompletableFuture.completedFuture(updatedUser);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Long id) {
		userRepository.delete(id);
	}



}
