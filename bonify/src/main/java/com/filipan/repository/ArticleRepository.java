package com.filipan.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.filipan.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

	@Query(value = "select article.* from user_server join server on user_server.server_id = server.id join article on server.id = article.server_id where user_server.user_id = ?1", 
			nativeQuery = true)
	Collection<Article> findAll(Long id);
	
	@Query(value = "select article.* from user_article join article on user_article.article_id = article.id where user_article.user_id = ?1 and user_article.read = ?2", 
			nativeQuery = true)
	Collection<Article> findAllRead(Long id, Boolean read);
	
}
