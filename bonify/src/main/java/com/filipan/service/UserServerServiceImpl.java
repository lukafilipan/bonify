package com.filipan.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filipan.model.UserServer;
import com.filipan.repository.UserServerRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServerServiceImpl implements UserServerService {

	@Autowired
	private UserServerRepository userServerRepository;
	
	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CompletableFuture<UserServer> subscribe(UserServer userServer) {
		if (userServer.getId() != null) {
			// Cannot create user
			return CompletableFuture.completedFuture(userServer);
		}
		UserServer savedUserServer= userServerRepository.save(userServer);
		return CompletableFuture.completedFuture(savedUserServer);
	}

	@Async
	@Override
	public void unsubscribe(Long id) {
		userServerRepository.delete(id);
	}

}
