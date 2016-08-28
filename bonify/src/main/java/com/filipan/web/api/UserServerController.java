package com.filipan.web.api;

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

import com.filipan.model.UserServer;
import com.filipan.service.UserServerService;

@RestController
public class UserServerController {

	@Autowired
	private UserServerService userServerService;
	
	@RequestMapping(value = "subscribe", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserServer> createUserServer(@RequestBody UserServer userServer) {

		UserServer subscribedServer;
		try {
			subscribedServer = userServerService.subscribe(userServer).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<UserServer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<UserServer>(subscribedServer, HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "subscribe/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserServer> deleteServer(@PathVariable("id") Long id) {

		userServerService.unsubscribe(id);
		return new ResponseEntity<UserServer>(HttpStatus.OK);

	}	
	
	
	
}
