package course.spring.intro.service.impl;

import course.spring.intro.dao.UserRepository;
import course.spring.intro.entity.User;
import course.spring.intro.exception.InvalidEntityDataException;
import course.spring.intro.exception.NonexistingEntityException;
import course.spring.intro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * UserService default implementation
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * @return list all {@link User} entities
     */
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    /**
     * @param id the ID of User to be found
     * @return The User with given ID, if such exists
     * @throws NonexistingEntityException when the User with given ID does not exist
     */
    @Override
    public User getUserById(Long id) throws NonexistingEntityException {
        return userRepo.findById(id).orElseThrow(() -> new NonexistingEntityException(
                String.format("Post with ID='%d' does not exist", id)
        ));
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User create(User user) throws InvalidEntityDataException{
        user.setId(null);
        if(userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new InvalidEntityDataException(
                    String.format("User with username '%s' already exists", user.getUsername()));
        }
        // TODO hash the password
        var now = LocalDateTime.now();
        user.setCreated(now);
        user.setModified(now);
        return userRepo.save(user);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public User update(User user) throws NonexistingEntityException, InvalidEntityDataException {
        var old = getUserById(user.getId());
        if (!old.getUsername().equals(user.getUsername())) {
            throw new InvalidEntityDataException(
                    String.format("User's username can not be changed from '%s' to '%s'",
                            old.getUsername(), user.getUsername()));
        }
        user.setCreated(old.getCreated());
        user.setModified(LocalDateTime.now());
        return userRepo.save(user);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public User deleteUserById(Long id) throws NonexistingEntityException {
        var old = getUserById(id);
        userRepo.deleteById(id);
        return old;
    }

    /**
     * @return
     */
    @Override
    public long getUsersCount() {
        return userRepo.count();
    }
}
