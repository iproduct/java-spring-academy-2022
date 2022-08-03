package dao;

import model.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User> {
    Optional<User> findByUsername(String username);
}
