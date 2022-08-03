package service;

import model.User;

import java.util.Collection;

public interface UserService {
    Collection<User> getAllUsers();
    User getUserById(Long id);
    User getUserUsername(String username);
    User addUser(User user);
    User updateUser(User user);
    User deleteUserById(Long id);
    long count();
}
