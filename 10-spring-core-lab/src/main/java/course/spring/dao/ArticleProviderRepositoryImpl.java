package course.spring.dao;

import course.spring.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository("repoProvider")
public class ArticleProviderRepositoryImpl extends RepositoryMemoryImpl<Article, Long>
        implements ArticleProvider {
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
        REPO_DEFAULT_ARTICLES.forEach(this::create);
    }

    @Autowired
    @Override
    public void setIdGenerator(IdGenerator<Long> idGenerator) {
        super.setIdGenerator(idGenerator);
    }

    @Override
    public List<Article> getArticles() {
        return new ArrayList<>(findAll());
    }
}
