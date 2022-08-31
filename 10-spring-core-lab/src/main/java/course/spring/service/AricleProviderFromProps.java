package course.spring.service;

import course.spring.model.Article;
import course.spring.qualifiers.FromProps;
import course.spring.service.ArticleProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@FromProps
@Service("fromProps")
@PropertySource("blog.properties")
@Order(3)
@Slf4j
public class AricleProviderFromProps implements ArticleProvider {
//    @Value("${blog.titles}")
    private String[] titles;

    private Environment environment;

    @Autowired
    public AricleProviderFromProps(Environment environment) {
        this.environment = environment;
    }

//    @Value("${blog.articles.count}")
    private int articlesCount;

    private List<Article> articles;

    @PostConstruct
    private void init() {
        String titlesStr = environment.getProperty("blog.titles");
        articlesCount = Integer.parseInt(environment.getProperty("blog.articles.count"));
        titles = titlesStr.split(",[\\s]*");
        articles = Arrays.stream(titles).map(title ->
                new Article(title, title + " content ...",
                        "http://myblogs.com/" + URLEncoder.encode(title), List.of(), "Trayan Iliev"))
                .collect(Collectors.toList());
        log.info("Successfully initilized {} articles from property file", articlesCount);
    }
    @Override
    public List<Article> getArticles() {
        return articles;
    }
}
