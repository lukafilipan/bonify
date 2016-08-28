package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filipan.model.Article;
import com.filipan.repository.ArticleRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Async
	@Override
	public CompletableFuture<Collection<Article>> findAllNew(Long id) {
		Collection<Article> articles = articleRepository.findAllRead(id, false);
		return  CompletableFuture.completedFuture(articles);
	}

	@Async
	@Override
	public CompletableFuture<Collection<Article>> findAll(Long id) {
		
		Collection<Article> articles = articleRepository.findAll(id);
		return  CompletableFuture.completedFuture(articles);
	}
	
	@Async
	@Override
	public CompletableFuture<Article> findOne(Long id) {
		Article article = articleRepository.findOne(id);
		return  CompletableFuture.completedFuture(article);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CompletableFuture<Article> create(Article article) {
		if (article.getId() != null) {
			// Cannot create user
			return  CompletableFuture.completedFuture(article);
		}
		Article savedArticle = articleRepository.save(article);
		return  CompletableFuture.completedFuture(savedArticle);
	}

	@Async
	@Override
	public void delete(Long id) {
		articleRepository.delete(id);

	}

}
