package course.spring.service;

import course.spring.dao.ArticleProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsoleArticlePresenter implements Presenter {
    private ApplicationContext ctx;
    private ArticleProvider provider;

    @Autowired
    void config(ApplicationContext ctx, @Qualifier("repoProvider") ArticleProvider provider) {
        this.ctx = ctx;
        this.provider = provider;
        log.info("Method based injection with two injected arguments: {} and {}", ctx, provider);
    }


//    @Autowired
//    public ConsoleArticlePresenter(ArticleProvider provider) {
//        this.provider = provider;
//    }


//    @Autowired
//    public void setProvider(ArticleProvider provider) {
//        this.provider = provider;
//    }

//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        ctx = applicationContext;
//    }

    @Override
    public void present() {
//        var provider = ctx.getBean(ArticleProvider.class);
        provider.getArticles().forEach(System.out::println);
    }

}
