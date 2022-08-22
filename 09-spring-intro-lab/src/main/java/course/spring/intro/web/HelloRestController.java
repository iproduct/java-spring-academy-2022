package course.spring.intro.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @GetMapping
    public String sayHello(){
        return "<h1>Hello from Spring REST Service!!!<h1>";
    }
}
