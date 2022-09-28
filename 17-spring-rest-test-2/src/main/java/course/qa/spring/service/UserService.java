package course.qa.spring.service;

import course.qa.spring.exception.InvalidEntityDataException;
import course.qa.spring.exception.NonexistingEntityException;
import course.qa.spring.model.User;

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
