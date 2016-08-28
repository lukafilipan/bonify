package com.filipan.service;

import java.util.concurrent.CompletableFuture;

import com.filipan.model.UserServer;

public interface UserServerService {
	
	CompletableFuture<UserServer> subscribe(UserServer userServer);
	
	void unsubscribe(Long id);
	
}
