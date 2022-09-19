package course.spring.blogs.service.impl;

import course.spring.blogs.entity.Article;
import course.spring.blogs.exception.InvalidEntityDataException;
import course.spring.blogs.exception.NonexistingEntityException;
import course.spring.blogs.service.ArticleService;
import course.spring.blogs.dao.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ArticleService default implementation
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepo;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    /**
     * @return list all {@link Article} entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    /**
     * @param id the ID of Article to be found
     * @return The Article with given ID, if such exists
     * @throws NonexistingEntityException when the Article with given ID does not exist
     */
    @Override
    @Transactional(readOnly = true)
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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Article create(Article article) {
        article.setId(null);
        var now = LocalDateTime.now();
        article.setCreated(now);
        article.setModified(now);
        return articleRepo.save(article);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Article> createBatch(List<Article> articles) {
        return articles.stream().map(this::create).collect(Collectors.toList());
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
    @Transactional(readOnly = true)
    public long getArticlesCount() {
        return articleRepo.count();
    }
}
