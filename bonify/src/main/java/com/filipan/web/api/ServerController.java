package com.filipan.web.api;

import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.filipan.model.Server;
import com.filipan.service.ServerService;

@RestController
public class ServerController {

	@Autowired
	private ServerService serverService;

	@RequestMapping(value = "servers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Server>> getServers() {

		Collection<Server> servers;
		try {
			servers = serverService.findAll().get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Collection<Server>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<Server>>(servers, HttpStatus.OK);

	}

	@RequestMapping(value = "user/{id}/subscribed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Server>> getSubscribed(@PathVariable("id") Long id) {

		Collection<Server> servers;
		try {
			servers = serverService.findAllSubscribed(id).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Collection<Server>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<Server>>(servers, HttpStatus.OK);

	}

	@RequestMapping(value = "user/{id}/unsubscribed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Server>> getUnsubscribed(@PathVariable("id") Long id) {

		Collection<Server> servers;
		try {
			servers = serverService.findAllUnsubscribed(id).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Collection<Server>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<Server>>(servers, HttpStatus.OK);

	}

	@RequestMapping(value = "servers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Server> getServer(@PathVariable("id") Long id) {

		Server server;
		try {
			server = serverService.findOne(id).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Server>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (server == null) {
			return new ResponseEntity<Server>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Server>(server, HttpStatus.OK);

	}

	@RequestMapping(value = "servers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Server> createServer(@RequestBody Server server) {

		Server savedServer;
		try {
			savedServer = serverService.create(server).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Server>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Server>(savedServer, HttpStatus.CREATED);

	}

	@RequestMapping(value = "servers/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Server> deleteServer(@PathVariable("id") Long id) {

		serverService.delete(id);
		return new ResponseEntity<Server>(HttpStatus.OK);

	}
}
