package course.spring.service;

import course.spring.dao.UserRepository;
import course.spring.model.Article;
import course.spring.model.User;
import course.spring.qualifiers.RepositoryBacked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RepositoryBacked
@Service
public class UserProviderRepositoryImpl implements UserProvider {
    private UserRepository userRepo;

    @Autowired
    public UserProviderRepositoryImpl(UserRepository userRepository) {
        this.userRepo = userRepository;
    }

    public static final List<User> REPO_DEFAULT_USERS = List.of(
            new User("Ivan", "Petrov", "ivan", "ivan123"),
            new User("John", "Doe", "john", "john123"),
            new User("Jane", "Doe", "jane", "jane123")
    );

    @PostConstruct
    public void init() {
        REPO_DEFAULT_USERS.forEach(userRepo::create);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(userRepo.findAll());
    }
}
