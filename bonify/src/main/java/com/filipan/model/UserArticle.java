package com.filipan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserArticle {

	@Id
	@GeneratedValue
	private Long id;
	private Boolean read;
	private Long userId;
	private Long articleId;

	public UserArticle() {
	}
	

	public UserArticle(Boolean read, Long userId, Long articleId) {
		super();
		this.read = read;
		this.userId = userId;
		this.articleId = articleId;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	};
	
}
