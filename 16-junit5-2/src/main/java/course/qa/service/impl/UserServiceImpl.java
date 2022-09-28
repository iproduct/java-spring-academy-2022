package course.qa.service.impl;

import course.qa.dao.UserRepository;
import course.qa.exception.InvalidEntityDataException;
import course.qa.exception.NonexistingEntityException;
import course.qa.model.User;
import course.qa.service.UserService;
import course.qa.util.EntityValidator;
import course.qa.util.UserValidator;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;
    private EntityValidator<User> userValidator;

    public UserServiceImpl(UserRepository userRepo, EntityValidator<User> userValidator) {
        this.userRepo = userRepo;
        this.userValidator = userValidator;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) throws NonexistingEntityException {
        return userRepo.findById(id).orElseThrow(
                () -> new NonexistingEntityException("User with ID='" + id + "' does not exist"));
    }

    @Override
    public User addUser(User user) throws InvalidEntityDataException {
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new InvalidEntityDataException("Username " + user.getUsername() + " already registered");
        }
        userValidator.validate(user);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        var created =  userRepo.create(user);
        log.debug("User '{}: {}' created successfully", created.getId(), created.getUsername());
        return created;
    }

    @Override
    public List<User> addUsersBatch(List<User> users) throws InvalidEntityDataException {
        List<User> results = new ArrayList<>();
        for(User user: users) {
            results.add(addUser(user));
        }
        return results;
    }

    @Override
    public User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException {
        userValidator.validate(user);
        user.setModified(LocalDateTime.now());
        return userRepo.update(user)
                .orElseThrow(() -> new NonexistingEntityException("User does not exist: " + user));
    }

    @Override
    public User deleteUserById(Long id) throws NonexistingEntityException {
        return userRepo.deleteById(id)
                .orElseThrow(() -> new NonexistingEntityException("User with ID='" + id + "' does not exist"));
    }

    @Override
    public long count() {
        return userRepo.count();
    }
}
