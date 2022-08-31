package course.spring.service;

import course.spring.qualifiers.FromProps;
import course.spring.qualifiers.RepositoryBacked;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service("articlePresenter")
@Slf4j
public class ConsoleArticlePresenter implements Presenter {
    private ApplicationContext ctx;
    private ArticleProvider provider;
//    private List<ArticleProvider> providers;

//    @Inject
//    void config(ApplicationContext ctx, @Mock ArticleProvider provider) {
//        this.ctx = ctx;
//        this.provider = provider;
//        log.info("Method based injection with two injected arguments: {} and {}", ctx, provider);
//    }


//    @Autowired
//    public ConsoleArticlePresenter(List<ArticleProvider> providers) {
//        this.providers = providers;
//    }


//    @Resource(name="fromProps")
    @Inject
    public void setProvider(ArticleProvider provider) {
        this.provider = provider;
    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        ctx = applicationContext;
//    }

    @Override
    public void present() {
//        var provider = ctx.getBean(ArticleProvider.class);
        provider.getArticles().forEach(System.out::println);
//        providers.stream()
//                .flatMap(provider -> provider.getArticles().stream())
//                .forEach(System.out::println);
    }

}
