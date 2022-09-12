package course.spring.blogs.web;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @GetMapping("/{name}")
    public String sayHelloNamePath(@PathVariable String name) {
        return String.format("<h1>Hello %s, from Spring REST Service, using @PathVariable!!!<h1>",
                name);
    }

    @GetMapping
    public String sayHello(@RequestParam(value = "name", required = false) String name) {
        return String.format("<h1>Hello %s, from Spring REST Service!!!<h1>",
                name != null ? name : "Anonymous");
    }

    @GetMapping("/users/{userId}/posts/{postId}")
    public String getUserPost(
            @PathVariable(name = "userId") Long userId,
            @MatrixVariable(name="mode", pathVar = "userId") String userMode,
            @PathVariable(name = "postId") Long postId,
            @MatrixVariable(name="mode", pathVar = "postId") String postMode
            ) {
        return String.format("User %d in mode %s -> Post %d in mode %s",
                userId, userMode, postId, postMode);
    }

    @GetMapping("/accept-header")
    public String getAcceptHeader(@RequestHeader("Accept") String acceptHeader){
        return String.format("Accept: %s", acceptHeader);
    }

    @GetMapping(value = "/all-headers", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getAllHeaders(@RequestHeader MultiValueMap<String, String> allHeaders){
        return allHeaders.toSingleValueMap();
    }

}
