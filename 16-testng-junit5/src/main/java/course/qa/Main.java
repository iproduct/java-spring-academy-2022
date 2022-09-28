package course.qa;

import course.qa.dao.IdGenerator;
import course.qa.dao.UserRepository;
import course.qa.dao.impl.LongIdGenerator;
import course.qa.dao.impl.UserRepositoryMemoryImpl;
import course.qa.exception.InvalidEntityDataException;
import course.qa.exception.NonexistingEntityException;
import course.qa.model.Role;
import course.qa.model.User;
import course.qa.service.UserService;
import course.qa.service.impl.UserServiceImpl;
import course.qa.util.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import static course.qa.model.Role.AUTHOR;

public class Main {
    public static void main(String[] args) {
        Logger log =  LoggerFactory.getLogger(Main.class);
        // Users demo
        List<User> users = List.of(
                new User("Ivan", "Petrov", 28, "ivan", "Ivan123#"),
                new User("John", "Doe", 42, "john", "John123#", Role.ADMIN,
                        "+(1) 23424242323", true),
                new User("Jane", "Doe", 34, "jane", "Jane123#", AUTHOR,
                        "+(359) 889654532", true)
        );

        // Create UserRepository
        IdGenerator<Long> longIdGenerator = new LongIdGenerator();
        UserRepository userRepo = new UserRepositoryMemoryImpl(longIdGenerator);

        // Create UserService
        UserService userService = new UserServiceImpl(userRepo, new UserValidator());
        try {
            userService.addUsersBatch(users);
        } catch (InvalidEntityDataException e) {
            log.error("Error creating default users", e);
        }

//        for(User user: userRepo.findAll()){
//            System.out.println(user);
//        }
//        userRepo.findAll().forEach(user -> System.out.println(user));
//        userService.getAllUsers().forEach(System.out::println);
        log.info("After delete:\n {}\n",
                userService.getAllUsers().stream()
                        .map(User::toString)
                        .collect(Collectors.joining("\n")));
        var john = userRepo.findByUsername("john").get();
        log.info("Before update: {}", john);

        //update user data
        john.setRole(AUTHOR);
        john.setPhone("+359 8888888888");
        john.setPassword("aaaaaaaA123#");
        john.setAge(45);
        try {
            userService.updateUser(john);
            var johnAfterUpdate = userService.getUserById(john.getId());
            log.info("After update: {}\n", johnAfterUpdate);
        } catch (NonexistingEntityException | InvalidEntityDataException e) {
            log.error("Error updating user: " + john.getUsername(), e);
        }

        // delete user
        try {
            userService.deleteUserById(john.getId());
            log.info("After delete:\n {}\n",
                    userService.getAllUsers().stream()
                            .map(User::toString)
                            .collect(Collectors.joining("\n")));

        } catch (NonexistingEntityException e) {
            log.error("Error deleting user by ID: " + john.getId(), e);
        }
    }
}
