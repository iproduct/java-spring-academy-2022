package course.spring.dao;

import course.spring.model.Article;

import java.util.List;

public interface ArticleProvider {
    List<Article> getArticles();
}
