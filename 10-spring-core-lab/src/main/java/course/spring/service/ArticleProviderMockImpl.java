package course.spring.service;

import course.spring.model.Article;
import course.spring.qualifiers.Mock;
import course.spring.service.ArticleProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
//@Primary
//@Mock
//@Service("mockProvider")
//@Order(1)
public class ArticleProviderMockImpl implements ArticleProvider {
    public static final List<Article> MOCK_ARTICLES = List.of(
            new Article("Intro to Spring", "Spring MVC is easy ...",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIyNvtJKwI_s4BqG7jh-g2Y4xa1mTAwLCHysTl42sZ2w&s",
                    List.of("spring", "intro"), "John Smith"),
            new Article("Hibernate Performance", "Hibernate provides powerful ORM implementation ...",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIyNvtJKwI_s4BqG7jh-g2Y4xa1mTAwLCHysTl42sZ2w&s",
                    List.of("hibernate", "orm", "performance"), "George Petrov"),
            new Article("Spring Boot is Easy", "Spring Boom makes new Spring projects easy ...",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIyNvtJKwI_s4BqG7jh-g2Y4xa1mTAwLCHysTl42sZ2w&s",
                    List.of("spring", "boot", "intro"), "John Smith")
    );
    @Override
    public List<Article> getArticles() {
        return MOCK_ARTICLES;
    }
}
