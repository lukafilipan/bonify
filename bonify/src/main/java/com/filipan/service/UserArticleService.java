package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import com.filipan.model.UserArticle;

public interface UserArticleService {
	
	CompletableFuture<Collection<UserArticle>> findAll(Long userId);
	
	CompletableFuture<UserArticle> findOne(Long id);
	
	CompletableFuture<UserArticle> create(UserArticle userArticle);
	
	CompletableFuture<UserArticle> update(UserArticle userArticle);
	
	void delete(Long id);

	

}
