package course.qa.spring.dao;

import course.qa.spring.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User> {
    public Optional<User> findByUsername(String username);
}
