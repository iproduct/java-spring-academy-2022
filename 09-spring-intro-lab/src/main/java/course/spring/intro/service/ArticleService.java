package course.spring.intro.service;

import course.spring.intro.entity.Article;
import course.spring.intro.exception.NonexistingEntityException;

import java.util.List;
/**
 * ArticleService interface
 */
public interface ArticleService {
    /**
     * @return list all {@link course.spring.intro.entity.Article} entities
     */
    List<Article> getAllArticles();
    /**
     * @param id the ID of Article to be found
     * @return The Article with given ID, if such exists
     * @throws NonexistingEntityException when the Article with given ID does not exist
     */
    Article getArticleById(Long id) throws NonexistingEntityException;
    Article create(Article article);
    Article update(Article article) throws NonexistingEntityException;
    Article deleteArticleById(Long id) throws NonexistingEntityException;
    long getArticlesCount();
}
