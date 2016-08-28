package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import com.filipan.model.Server;

public interface ServerService {

	CompletableFuture<Collection<Server>> findAll();
	
	CompletableFuture<Collection<Server>> findAllSubscribed(Long id);
	
	CompletableFuture<Collection<Server>> findAllUnsubscribed(Long id);

	CompletableFuture<Server> findOne(Long id);

	CompletableFuture<Server> create(Server server);

	void delete(Long i);

}
