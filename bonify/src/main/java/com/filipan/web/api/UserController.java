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

import com.filipan.model.User;
import com.filipan.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {

		Collection<User> users;

		try {
			users = userService.findAll().get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Collection<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

		User user;
		try {
			user = userService.findOne(id).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	@RequestMapping(value = "users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User savedUser;
		try {
			savedUser = userService.create(user).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);

	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {

		User updatedUser;
		try {
			updatedUser = userService.update(user).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (updatedUser == null) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);

	}

	@RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {

		userService.delete(id);
		return new ResponseEntity<User>(HttpStatus.OK);

	}

}
