import dao.UserRepository;
import dao.impl.UserRepoFactory;
import exception.ConstraintViolationException;
import exception.NonexistingEntityException;
import model.Person;
import model.Role;
import model.User;
import model.builder.PersonBuilder;
import service.UserService;
import service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static final List<User> MOCK_USERS = List.of(
            new User("James", "Doe", 16, "james", "james123", Role.ADMIN),
            new User("Jane", "Doe", 16, "ja", "jane123", Role.AUTHOR),
            new User("John", "Smith", 16, "john", "john123")
    );
    public static void main(String[] args) {
        // Users demo
        UserRepository userRepo = new UserRepoFactory().createUserRepository();
        UserService userService = new UserServiceImpl(userRepo);

        for(User u : MOCK_USERS) {
            try {
                userService.addUser(u);
            } catch (ConstraintViolationException e) {
                System.out.printf("Invalid user data: %s%n", e.getViolations());
            }
        }
        userService.getAllUsers().forEach(System.out::println);

        // find user by username
        User john = null;
        try {
            john = userService.getUserUsername("john");
            System.out.printf("User found: %s%n", john);
        } catch (NonexistingEntityException e) {
            System.out.printf("Error finding user by username: " + e.getMessage());
        }

    }
}
