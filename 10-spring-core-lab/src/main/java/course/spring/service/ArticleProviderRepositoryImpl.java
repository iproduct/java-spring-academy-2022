package course.spring.service;

import course.spring.dao.ArticleRepository;
import course.spring.model.Article;
import course.spring.qualifiers.RepositoryBacked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RepositoryBacked
@Service("repoProvider")
@Order(2)
public class ArticleProviderRepositoryImpl implements ArticleProvider {
    private ArticleRepository articleRepo;

    @Autowired
    public ArticleProviderRepositoryImpl(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    public static final List<Article> REPO_DEFAULT_ARTICLES = List.of(
            new Article("Spring Data JPA Intro", "Spring Data JPA is easy ...",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIyNvtJKwI_s4BqG7jh-g2Y4xa1mTAwLCHysTl42sZ2w&s",
                    List.of("spring data", "jpa", "intro"), "Ivan Petrov"),
            new Article("Spring Data JPA and Hibernate", "JPA and Hibernate provide powerful ORM implementation ...",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIyNvtJKwI_s4BqG7jh-g2Y4xa1mTAwLCHysTl42sZ2w&s",
                    List.of("hibernate", "orm", "jpa"), "Ivan Petrov"),
            new Article("Spring Data is Going Reactive", "Spring Data provides a number of reactive DB integrations ...",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIyNvtJKwI_s4BqG7jh-g2Y4xa1mTAwLCHysTl42sZ2w&s",
                    List.of("spring", "data", "reactive"), "Ivan Petrov")
    );

    @PostConstruct
    public void init() {
        REPO_DEFAULT_ARTICLES.forEach(articleRepo::create);
    }

//    @Autowired
//    @Override
//    public void setIdGenerator(IdGenerator<Long> idGenerator) {
//        articleRepo.setIdGenerator(idGenerator);
//    }

    @Override
    public List<Article> getArticles() {
        return new ArrayList<>(articleRepo.findAll());
    }
}
