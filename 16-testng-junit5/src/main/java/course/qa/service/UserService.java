package course.qa.service;

import course.qa.exception.InvalidEntityDataException;
import course.qa.exception.NonexistingEntityException;
import course.qa.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id) throws NonexistingEntityException;
    User addUser(User user) throws InvalidEntityDataException;
    List<User> addUsersBatch(List<User> users) throws InvalidEntityDataException;
    User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException;
    User deleteUserById(Long id) throws NonexistingEntityException;
    long count();
}
