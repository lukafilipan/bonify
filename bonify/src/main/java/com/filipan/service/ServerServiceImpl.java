package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filipan.model.Server;
import com.filipan.repository.ServerRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerRepository serverRepository;
	
	@Async
	@Override
	public CompletableFuture<Collection<Server>> findAll() {
		Collection<Server> servers = serverRepository.findAll();
		return CompletableFuture.completedFuture(servers);
	}
	
	@Async
	@Override
	public CompletableFuture<Collection<Server>> findAllSubscribed(Long id) {
		Collection<Server> servers = serverRepository.findAllSubscribed(id);
		return CompletableFuture.completedFuture(servers);
	}

	@Async
	@Override
	public CompletableFuture<Collection<Server>> findAllUnsubscribed(Long id) {
		Collection<Server> servers = serverRepository.findAllUnsubscribed(id);
		return CompletableFuture.completedFuture(servers);
	}
	
	@Async
	@Override
	public CompletableFuture<Server> findOne(Long id) {
		Server server = serverRepository.findOne(id);
		return CompletableFuture.completedFuture(server);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CompletableFuture<Server> create(Server server) {
		if (server.getId() != null) {
			// Cannot create user
			return CompletableFuture.completedFuture(server);
		}
		Server savedServer= serverRepository.save(server);
		return CompletableFuture.completedFuture(savedServer);
	}

	@Async
	@Override
	public void delete(Long id) {
		serverRepository.delete(id);
	}

}
