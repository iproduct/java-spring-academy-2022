package course.spring.service;

import course.spring.model.Article;
import course.spring.model.User;

import java.util.List;

public interface UserProvider {
    List<User> getUsers();
}
