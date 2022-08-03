package service.impl;

import dao.UserRepository;
import exception.ConstraintViolation;
import exception.ConstraintViolationException;
import exception.InvalidEntityDataException;
import exception.NonexistingEntityException;
import model.User;
import service.UserService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    public interface UserValidator{
        void validate() throws ConstraintViolationException;
    }
    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) throws NonexistingEntityException {
        return userRepo.findById(id).orElseThrow(() ->
                new NonexistingEntityException(String.format("User with ID='%d' does not exist")));
    }

    @Override
    public User getUserUsername(String username) throws NonexistingEntityException {
        return userRepo.findByUsername(username).orElseThrow(() ->
                new NonexistingEntityException(String.format("User with Username  '%d' does not exist")));
    }

    @Override
    public User addUser(final User user) throws ConstraintViolationException {
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            new InvalidEntityDataException(String.format("User with Username '%d' already exists"));
        }
        UserValidator validator = new UserValidator() {
            @Override
            public void validate() throws ConstraintViolationException {
                if(user.getUsername().length() < 3) {
                    throw new ConstraintViolationException("Invalid user data", List.of(
                            new ConstraintViolation("User", "username", user.getUsername(),
                                    "Username should be at least 3 characters long")
                    ));
                }
            }
        };
        validator.validate();
        return userRepo.create(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User deleteUserById(Long id) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
