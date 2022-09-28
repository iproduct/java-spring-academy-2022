package course.qa.spring.service.impl;

import course.qa.spring.dao.UserJpaRepository;
import course.qa.spring.dao.UserRepository;
import course.qa.spring.exception.InvalidEntityDataException;
import course.qa.spring.exception.NonexistingEntityException;
import course.qa.spring.model.User;
import course.qa.spring.service.UserService;
import course.qa.spring.util.EntityValidator;
import course.qa.spring.util.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private UserJpaRepository userRepo;
    private UserValidator userValidator;

    public UserServiceImpl(UserJpaRepository userRepo, UserValidator userValidator) {
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
        var created =  userRepo.save(user);
//        log.debug("User '{}: {}' created successfully", created.getId(), created.getUsername());
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
        User old = getUserById(user.getId());
        userValidator.validate(user);
        user.setModified(LocalDateTime.now());
        return userRepo.save(user);
    }

    @Override
    public User deleteUserById(Long id) throws NonexistingEntityException {
        User old = getUserById(id);
        userRepo.deleteById(id);
        return old;
    }

    @Override
    public long count() {
        return userRepo.count();
    }
}
