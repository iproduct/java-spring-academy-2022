package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.UserRepository;
import course.java.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//@Repository
class UserRepositoryMemoryImpl extends RepositoryMemoryImpl<User, Long> implements UserRepository {

    public UserRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Optional<User> findByUsername(String username) {
//        for(var user : entities.values()) {
//            if(user.getUsername().equals(username)){
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
        return findAll().stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

}
