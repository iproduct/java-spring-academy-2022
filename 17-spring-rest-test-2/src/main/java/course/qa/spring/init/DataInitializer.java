package course.qa.spring.init;

import course.qa.spring.model.Role;
import course.qa.spring.model.User;
import course.qa.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static course.qa.spring.model.Role.AUTHOR;
import static course.qa.spring.model.Role.READER;

@Slf4j
@Component
@Profile("!test")
public class DataInitializer implements CommandLineRunner {
    public static final List<User> DEFAULT_USERS = List.of(
            new User("Default", "Admin", 20, "admin", "Admin123#"),
            new User("John", "Doe", 42, "john", "John123#", READER,
                    "+(1) 23424242323", true),
            new User("Jane", "Doe", 34, "jane", "Jane123#", AUTHOR,
                    "+(359) 889654532", true)
    );

    private UserService userService;

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userService.count() == 0) {
            var created = userService.addUsersBatch(DEFAULT_USERS);
            log.info("Default users created:\n{}",
                    created.stream().map(User::toString).collect(Collectors.joining("\n")));
        }
    }
}
