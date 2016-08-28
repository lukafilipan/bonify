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

import com.filipan.model.Article;
import com.filipan.model.UserArticle;
import com.filipan.service.ArticleService;
import com.filipan.service.UserArticleService;

@RestController
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private UserArticleService userArticleService;

	@RequestMapping(value = "articles", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> createArticle(@RequestBody Article article) {

		Article savedArticle;
		try {
			savedArticle = articleService.create(article).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Article>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Article>(savedArticle, HttpStatus.CREATED);

	}

	// Requesting all articles for a specific user
	@RequestMapping(value = "articles/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Article>> getAllArticles(@PathVariable("id") Long id) {

		Collection<Article> articles;
		try {
			articles = articleService.findAll(id).get();

			// When the user retrieves the articles, set them as new
			for (Article a : articles) {
				userArticleService.create(new UserArticle(false, id, a.getId()));
			}
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Collection<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<Article>>(articles, HttpStatus.OK);

	}

	@RequestMapping(value = "articles/new/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Article>> getNewArticles(@PathVariable("id") Long id) {

		Collection<Article> articles;
		try {
			articles = articleService.findAllNew(id).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Collection<Article>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Collection<Article>>(articles, HttpStatus.OK);

	}

	@RequestMapping(value = "articles/{user_id}/{article_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Article> getArticle(@PathVariable("user_id") Long userId,
			@PathVariable("article_id") Long articleId) {

		Article article;
		try {
			article = articleService.findOne(articleId).get();
		} catch (InterruptedException | ExecutionException e) {
			return new ResponseEntity<Article>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		userArticleService.update(new UserArticle(false, userId, article.getId()));

		return new ResponseEntity<Article>(article, HttpStatus.OK);

	}

}
