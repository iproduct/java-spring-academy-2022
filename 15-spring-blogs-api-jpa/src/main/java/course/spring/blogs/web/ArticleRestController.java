package course.spring.blogs.web;

import course.spring.blogs.entity.Article;
import course.spring.blogs.exception.InvalidEntityDataException;
import course.spring.blogs.service.ArticleService;
import course.spring.blogs.utils.ErrorHandlingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/articles")
public class ArticleRestController {
    @Autowired
    private ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id:\\d+}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public ResponseEntity<Article> addNewArticle(@Valid @RequestBody Article article, Errors errors) {
        ErrorHandlingUtils.handleValidationErrors(errors);

        var created = articleService.create(article);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

    @PutMapping("/{id}")
    public Article updateAticle(@PathVariable("id") Long id, @Valid @RequestBody Article article, Errors errors) {
        ErrorHandlingUtils.handleValidationErrors(errors);

        if(!id.equals(article.getId())) {
            throw new InvalidEntityDataException(
                    String.format("ID in URL='%d' is different from ID in message body = '%d'", id, article.getId()));
        }
        return articleService.update(article);
    }

    @DeleteMapping("/{id}")
    public Article deleteArticleById(@PathVariable("id") Long id) {
        return articleService.deleteArticleById(id);
    }

    @GetMapping(value = "/count", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getArticlsCount() {
        return Long.toString(articleService.getArticlesCount());
    }



}
