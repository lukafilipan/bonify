package com.filipan.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.filipan.model.UserArticle;

@Repository
public interface UserArticleRepository extends JpaRepository<UserArticle, Long> {

	@Query(value = "select * from user_article where user_article.user_id = ?1", 
			nativeQuery = true)
	Collection<UserArticle> findAll(Long id);
		
}
