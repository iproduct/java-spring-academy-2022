package course.qa.dao;

import course.qa.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<Long, User> {
    public Optional<User> findByUsername(String username);
}
