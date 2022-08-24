package course.spring.intro.web;

import course.spring.intro.entity.Article;
import course.spring.intro.exception.InvalidEntityDataException;
import course.spring.intro.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable("id") Long id) {
        return articleService.getArticleById(id);
    }

    @PostMapping
    public ResponseEntity<Article> addNewArticle(@RequestBody Article article) {
        var created = articleService.create(article);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().pathSegment("{id}")
                        .buildAndExpand(created.getId()).toUri()
        ).body(created);
    }

    @PutMapping("/{id}")
    public Article updateAticle(@PathVariable("id") Long id, @RequestBody Article article) {
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


}
