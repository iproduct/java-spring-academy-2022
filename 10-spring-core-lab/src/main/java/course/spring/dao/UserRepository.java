package course.spring.dao;

import course.spring.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long>{
    Optional<User> findByUsername(String username);
}
