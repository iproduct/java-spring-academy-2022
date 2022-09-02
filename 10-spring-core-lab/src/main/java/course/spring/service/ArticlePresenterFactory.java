package course.spring.service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ArticlePresenterFactory {
    private ArticleProvider provider;

    public ArticlePresenterFactory(ArticleProvider provider) {
        this.provider = provider;
    }

    public Presenter createArticlePresenter() {
        log.info("Creating ConsoleArticlePresenter using dedicated FACTORY CLASS.");
        return new ConsoleArticlePresenter(provider);
    }
}
