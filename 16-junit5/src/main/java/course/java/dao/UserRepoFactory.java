package course.java.dao;

import java.util.Map;
import java.util.Properties;

public interface UserRepoFactory {
    UserRepository createUserRepository(Properties options);
}
