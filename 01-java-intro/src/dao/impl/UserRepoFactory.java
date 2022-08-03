package dao.impl;

import dao.UserRepository;

public class UserRepoFactory {
    public UserRepository createUserRepository(){
        return new UserRepositoryInMemoryImpl(new LongIdGenerator());
    }
}
