package dao.impl;

import dao.IdGenerator;
import dao.UserRepository;
import model.User;

import java.util.Optional;

class UserRepositoryInMemoryImpl extends RepositoryInMemoryImpl<Long, User> implements UserRepository {
    public UserRepositoryInMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        for(User u :findAll()){
            if(u.getUsername().equals(username)){
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }
}
