package course.spring.intro.service.impl;

import course.spring.intro.dao.ArticleRepository;
import course.spring.intro.entity.Article;
import course.spring.intro.exception.InvalidEntityDataException;
import course.spring.intro.exception.NonexistingEntityException;
import course.spring.intro.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return articleRepo.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Post with ID='%d' does not exist", id)
        ));
    }

    /**
     * @param article
     * @return
     */
    @Override
    public Article create(Article article) {
        article.setId(null);
        var now = LocalDateTime.now();
        article.setCreated(now);
        article.setModified(now);
        return articleRepo.save(article);
    }

    /**
     * @param article
     * @return
     */
    @Override
    public Article update(Article article) throws NonexistingEntityException, InvalidEntityDataException {
        var old = getArticleById(article.getId());
        if (!old.getAuthor().equals(article.getAuthor())) {
            throw new InvalidEntityDataException(
                    String.format("Post author can not be changed from '%s' to '%s'",
                            old.getAuthor(), article.getAuthor()));
        }
        article.setCreated(old.getCreated());
        article.setModified(LocalDateTime.now());
        return articleRepo.save(article);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Article deleteArticleById(Long id) throws NonexistingEntityException {
        var old = getArticleById(id);
        articleRepo.deleteById(id);
        return old;
    }

    /**
     * @return
     */
    @Override
    public long getArticlesCount() {
        return articleRepo.count();
    }
}
