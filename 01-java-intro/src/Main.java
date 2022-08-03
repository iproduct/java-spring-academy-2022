import dao.UserRepository;
import dao.impl.LongIdGenerator;
import dao.impl.UserRepositoryInMemoryImpl;
import model.Person;
import model.Role;
import model.User;
import model.builder.PersonBuilder;

import java.util.List;

public class Main {
    public static final List<User> MOCK_USERS = List.of(
            new User("James", "Doe", 16, "james", "james123", Role.ADMIN),
            new User("Jane", "Doe", 16, "jane", "jane123", Role.AUTHOR),
            new User("John", "Smith", 16, "john", "john123")
    );
    public static void main(String[] args) {
        // Users demo
        UserRepository userRepo = new UserRepositoryInMemoryImpl(new LongIdGenerator());
        MOCK_USERS.forEach(userRepo::create);
        userRepo.findAll().forEach(System.out::println);

        // find user by username
        var john = userRepo.findByUsername("john");
        System.out.printf("User found: %s%n", john);
    }
}
