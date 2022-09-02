package course.spring.dao;

import course.spring.model.Article;
import org.springframework.stereotype.Repository;

//@Repository
public class ArticleRepositoryImpl extends RepositoryMemoryImpl<Article, Long>
    implements ArticleRepository {
    public ArticleRepositoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
