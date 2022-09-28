package course.qa.service.impl.stubs;

import course.qa.dao.UserRepository;
import course.qa.model.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryStub implements UserRepository {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User create(User entity) {
        entity.setId(1L);
        return entity;
    }

    @Override
    public Optional<User> update(User entity) {
        return Optional.empty();
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
