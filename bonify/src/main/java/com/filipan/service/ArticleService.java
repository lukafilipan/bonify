package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import com.filipan.model.Article;

public interface ArticleService {

	CompletableFuture<Collection<Article>> findAllNew(Long id);
	
	CompletableFuture<Collection<Article>> findAll(Long id);
	
	CompletableFuture<Article> findOne(Long id);

	CompletableFuture<Article> create(Article article);
	
	void delete(Long id);
	
}
