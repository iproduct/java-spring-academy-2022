package course.qa.spring.dao;

import course.qa.spring.model.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static course.qa.spring.model.Role.AUTHOR;
import static course.qa.spring.model.Role.READER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource("/application-test.properties")
@Slf4j
class UserJpaRepositoryTest {

    @Autowired
    UserJpaRepository userRepo;

    @BeforeEach
    void setUp() {
        userRepo.saveAll(DEFAULT_USERS);
    }

    @AfterEach
    void tearDown() {
        userRepo.deleteAll();
    }

    @Test
    void findAll() {
        var actual = userRepo.findAll();
        assertThat(actual, hasSize(3));
        log.info("First user: {}", actual.get(0));
        assertThat(actual.get(0),
                samePropertyValuesAs(EXPECTED_USERS.get(0),  "id", "created", "modified"));
    }

    @Test
    void findByUsername() {
        var actual = userRepo.findByUsername(DEFAULT_USERS.get(2).getUsername());
        assertTrue(actual.isPresent());
        assertThat(actual.get(),
                samePropertyValuesAs(EXPECTED_USERS.get(2),  "id", "created", "modified"));
    }

    public static final List<User> DEFAULT_USERS = List.of(
            new User("Default", "Admin", 20, "admin", "Admin123#"),
            new User("John", "Doe", 42, "john", "John123#", READER,
                    "+(1) 23424242323", true),
            new User("Jane", "Doe", 34, "jane", "Jane123#", AUTHOR,
                    "+(359) 889654532", true)
    );
    public static final List<User> EXPECTED_USERS = List.of(
            new User("Default", "Admin", 20, "admin", "Admin123#"),
            new User("John", "Doe", 42, "john", "John123#", READER,
                    "+(1) 23424242323", true),
            new User("Jane", "Doe", 34, "jane", "Jane123#", AUTHOR,
                    "+(359) 889654532", true)
    );
}
