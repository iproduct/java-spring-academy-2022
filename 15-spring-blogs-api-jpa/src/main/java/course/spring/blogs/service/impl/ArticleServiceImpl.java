package course.spring.blogs.service.impl;

import course.spring.blogs.entity.Article;
import course.spring.blogs.events.ArticleCreatedEvent;
import course.spring.blogs.exception.InvalidEntityDataException;
import course.spring.blogs.exception.NonexistingEntityException;
import course.spring.blogs.service.ArticleService;
import course.spring.blogs.dao.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.transaction.TransactionDefinition.ISOLATION_REPEATABLE_READ;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * ArticleService default implementation
 */
@Service
@Transactional
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    private ArticleRepository articleRepo;
    //    private TransactionTemplate template;
    private PlatformTransactionManager transactionManager;
    private ApplicationEventPublisher publisher;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepo,
                              TransactionTemplate template,
                              PlatformTransactionManager manager,
                              ApplicationEventPublisher publisher) {
        this.articleRepo = articleRepo;
//        this.template = template;
        this.transactionManager = manager;
        this.publisher = publisher;
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
        var created = articleRepo.save(article);
        publisher.publishEvent(new ArticleCreatedEvent(created));
        return created;
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public List<Article> createBatch(List<Article> articles) {
//        return articles.stream().map(this::create).collect(Collectors.toList());
//    }

//    @Override
//    public List<Article> createBatch(List<Article> articles) {
//        return template.execute(new TransactionCallback<List<Article>>() {
//            @Override
//            public List<Article> doInTransaction(TransactionStatus status) {
//                return articles.stream()
//                        .map(article -> {
//                            try {
//                                return create(article);
//                            } catch (ConstraintViolationException ex) {
//                                log.error(">>> Constraint violation creating article: " + article.toString(), ex);
//                                status.setRollbackOnly();
//                                return null;
//                            }
//                        }).collect(Collectors.toList());
//            }
//        });
//    }

    @Override
    public List<Article> createBatch(List<Article> articles) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
        def.setTimeout(5);
        def.setName("createArticlesBatchTransaction");
        def.setIsolationLevel(ISOLATION_REPEATABLE_READ);

        // Do in transaction
        List<Article> createdArticles = List.of();
        var status = transactionManager.getTransaction(def);
        try {
            createdArticles = articles.stream().map(this::create).collect(Collectors.toList());
        } catch (ConstraintViolationException ex) {
            log.error(">>> Constraint violation creating articles: " + articles.toString(), ex);
            transactionManager.rollback(status);
            return createdArticles;
        }
        transactionManager.commit(status);
        return createdArticles;
    }


    /**
     * @param article
     * @return
     */
    @Override
    public Article update(Article article) throws NonexistingEntityException, InvalidEntityDataException {
        var old = getArticleById(article.getId());
        if (!old.getAuthors().equals(article.getAuthors())) {
            throw new InvalidEntityDataException(
                    String.format("Post author can not be changed from '%s' to '%s'",
                            old.getAuthors(), article.getAuthors()));
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

    @TransactionalEventListener
    public void handleArticleCreatedTransactionCommit(ArticleCreatedEvent event) {
        log.info(">>>>> Transaction COMMIT for article: {}", event.article());
    }
    @TransactionalEventListener(phase= TransactionPhase.AFTER_ROLLBACK)
    public void handleArticleCreatedTransactionRollback(ArticleCreatedEvent event) {
        log.info(">>>>> Transaction ROLLBACK for article: {}", event.article());
    }
}
