package com.filipan.service;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.filipan.model.UserArticle;
import com.filipan.repository.UserArticleRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserArticleServiceImpl implements UserArticleService {

	@Autowired
	private UserArticleRepository userArticleRepository;

	@Async
	@Override
	public CompletableFuture<Collection<UserArticle>> findAll(Long userId) {
		Collection<UserArticle> userArticles = userArticleRepository.findAll(userId);
		return CompletableFuture.completedFuture(userArticles);
	}

	@Async
	@Override
	public CompletableFuture<UserArticle> findOne(Long id) {
		UserArticle userArticle = userArticleRepository.findOne(id);
		return CompletableFuture.completedFuture(userArticle);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CompletableFuture<UserArticle> create(UserArticle userArticle) {
		if (userArticle.getId() != null) {
			// Cannot create user
			return CompletableFuture.completedFuture(userArticle);
		}
		UserArticle savedUserArticle = userArticleRepository.save(userArticle);
		return CompletableFuture.completedFuture(savedUserArticle);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public CompletableFuture<UserArticle> update(UserArticle userArticle) {
		UserArticle userArticlePersisted = userArticleRepository.findOne(userArticle.getId());
		if (userArticlePersisted == null) {
			// Cannot update user
			return null;
		}
		UserArticle updatedUserArticle = userArticleRepository.save(userArticle);
		return CompletableFuture.completedFuture(updatedUserArticle);
	}

	@Async
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(Long id) {
		userArticleRepository.delete(id);

	}

}
