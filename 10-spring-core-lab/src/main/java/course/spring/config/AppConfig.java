package course.spring.config;

import course.SecondConfig;
import course.spring.dao.ArticleRepository;
import course.spring.dao.ArticleRepositoryImpl;
import course.spring.dao.IdGenerator;
import course.spring.dao.LongIdGenerator;
import course.spring.model.Article;
import course.spring.qualifiers.FromProps;
import course.spring.qualifiers.Mock;
import course.spring.qualifiers.RepositoryBacked;
import course.spring.service.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@ComponentScan("course.spring")
//@ImportResource({"app-context.xml"})
@Import(SecondConfig.class)
public class AppConfig {
    @Bean
    @Scope("prototype")
    public IdGenerator<Long> longIdGenerator() {
        return new LongIdGenerator();
    }

    @Order(2)
    @Mock
    @Bean("mockProvider")
    public ArticleProvider mockArticleProvider() {
        return new ArticleProviderMockImpl();
    }

    @Bean("articlesRepo")
    public ArticleRepository articlesRepository() {
        return new ArticleRepositoryImpl(longIdGenerator());
    }

    @Order(1)
    @RepositoryBacked
    @Bean(name="repoProvider", initMethod = "init")
    public ArticleProvider repoArticleProvider() {
        return new ArticleProviderRepositoryImpl(articlesRepository());
    }

    @Order(3)
    @FromProps
    @Bean(name="propsProvider", initMethod = "init")
    public ArticleProvider fromPropsArticleProvider() {
        return new AricleProviderFromProps();
    }



//    @Bean("articlePresenter")
//    public Presenter consoleArticlePresenter(List<ArticleProvider> providers) {
//        return new ConsoleArticlePresenter(() -> providers
//                .stream().flatMap(provider -> provider.getArticles().stream())
//                        .collect(Collectors.toList())
//        );
//    }

    @Bean(name="articlePresenter")
    public Presenter consoleArticlePresenter(@FromProps ArticleProvider provider) {
        return new ConsoleArticlePresenter(provider);
    }


}
