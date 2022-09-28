package course.qa.spring.dao.impl;

import course.qa.spring.dao.IdGenerator;
import course.qa.spring.dao.UserRepository;
import course.qa.spring.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
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
