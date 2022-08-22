package course.spring.intro.service.impl;

import course.spring.intro.dao.ArticleRepository;
import course.spring.intro.entity.Article;
import course.spring.intro.exception.NonexistingEntityException;
import course.spring.intro.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ArticleService default implementation
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepo;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    /**
     * @return list all {@link course.spring.intro.entity.Article} entities
     */
    @Override
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    /**
     * @param id the ID of Article to be found
     * @return The Article with given ID, if such exists
     * @throws NonexistingEntityException when the Article with given ID does not exist
     */
    @Override
    public Article getArticleById(Long id) throws NonexistingEntityException {
        return null;
    }

    /**
     * @param article
     * @return
     */
    @Override
    public Article create(Article article) {
        article.setId(null);
        return articleRepo.save(article);
    }

    /**
     * @param article
     * @return
     */
    @Override
    public Article update(Article article) throws NonexistingEntityException {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Article deleteArticleById(Long id) throws NonexistingEntityException {
        return null;
    }

    /**
     * @return
     */
    @Override
    public long getArticlesCount() {
        return 0;
    }
}
