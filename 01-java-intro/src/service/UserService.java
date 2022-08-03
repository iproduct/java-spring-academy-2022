package service;

import exception.ConstraintViolationException;
import exception.NonexistingEntityException;
import model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getAllUsers();
    User getUserById(Long id) throws NonexistingEntityException;
    User getUserUsername(String username) throws NonexistingEntityException;
    User addUser(User user) throws ConstraintViolationException;
    User updateUser(User user);
    User deleteUserById(Long id);
    long count();
}
