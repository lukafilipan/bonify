package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import com.filipan.model.User;

public interface UserService {

	CompletableFuture<Collection<User>> findAll();
	
	CompletableFuture<User> findOne(Long id);
	
	CompletableFuture<User> create(User user);
	
	CompletableFuture<User> update(User user);
	
	void delete(Long id);
	
}
