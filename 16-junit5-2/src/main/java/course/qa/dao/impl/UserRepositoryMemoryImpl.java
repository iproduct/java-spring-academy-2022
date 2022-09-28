package course.qa.dao.impl;

import course.qa.dao.IdGenerator;
import course.qa.dao.UserRepository;
import course.qa.model.User;

import java.util.Optional;

public class UserRepositoryMemoryImpl extends RepositoryMemoryImpl<Long, User> implements UserRepository {
    public UserRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }
}
