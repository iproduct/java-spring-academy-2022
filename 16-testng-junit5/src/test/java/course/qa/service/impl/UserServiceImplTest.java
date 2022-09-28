package course.qa.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.qa.dao.UserRepository;
import course.qa.exception.InvalidEntityDataException;
import course.qa.model.Role;
import course.qa.model.User;
import course.qa.service.impl.stubs.UserRepositoryStub;
import course.qa.service.impl.stubs.UserValidatorStub;
import course.qa.util.EntityValidator;
import course.qa.util.UserValidator;
import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InvalidClassException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    private static List<User> SAMPLE_USERS;

    @Spy
    private UserValidator userValidator;

    @BeforeAll
    public static void setup() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        URL usersJsonFileUrl = UserServiceImplTest.class.getClassLoader().getResource("users.json");
        try {
            TypeReference<List<User>> userList = new TypeReference<List<User>>() {
            };
            SAMPLE_USERS = mapper.readValue(usersJsonFileUrl, userList);
            log.info("User loaded from JSON file: {}",
                    SAMPLE_USERS.stream().map(User::toString).collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    @Test
//    void getAllUsers() {
//    }
//
//    @Test
//    void getUserById() {
//    }

    @TestFactory
    Stream<DynamicTest> addUser() {
        return SAMPLE_USERS.stream().map(user ->
                        dynamicTest("Create user with JSON file data '" + user.getUsername() + "'", () -> {
                            var userService = new UserServiceImpl(new UserRepositoryStub(), new UserValidatorStub());
//                            new UserRepositoryMemoryImpl(new LongIdGenerator()), new UserValidator());
                            User actual = userService.addUser(user);
                            assertNotNull(actual);
                            assertNotNull(actual.getId());
                            assertEquals(Long.valueOf(1L), actual.getId());
                        })
        );
    }

    @TestFactory
    Stream<DynamicTest> addUserMockito() {
        return SAMPLE_USERS.stream().map(user ->
                dynamicTest("Create user with JSON file data '" + user.getUsername() + "'", () -> {
                    // 1) Mock dependencies using Mockito
                    var created = cloneUser(user);
                    created.setId(1L);
                    created.setCreated(LocalDateTime.now());
                    created.setModified(LocalDateTime.now());
                    var mockUserRepository = mock(UserRepository.class);
                    when(mockUserRepository.create(user)).thenReturn(created);
                    when(mockUserRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

                    var validatorSpy = spy(UserValidator.class);

                    var userService = new UserServiceImpl(mockUserRepository, validatorSpy);
                    User actual = userService.addUser(user);
                    assertNotNull(actual);
                    assertNotNull(actual.getId());
                    assertEquals(Long.valueOf(1L), actual.getId());

                    verify(mockUserRepository, times(1)).create(any(User.class));
                    verify(mockUserRepository, times(1)).findByUsername(anyString());
                    verifyNoMoreInteractions(mockUserRepository);

                    verify(validatorSpy, times(1)).validate(any(User.class));
                    verifyNoMoreInteractions(validatorSpy);
                })
        );
    }

    @Test
    @DisplayName("When given user with existing username, the create method should fail with InvalidEntityException")
    public void givenUsernameExists_whenCreate_thenInvalidEntityExceptionThrown() {
        // 1) Mock dependencies using Mockito
        when(mockUserRepository.findByUsername(CREATED_USER.getUsername())).thenReturn(Optional.of(CREATED_USER));
//        var userValidatorMock = when(EntityValidator.class).thenThrow(new InvalidEntityDataException("Invalid fields"));

        var userService = new UserServiceImpl(mockUserRepository, userValidator);
        var user = cloneUser(CREATED_USER);
        user.setId(null);
        var exception = assertThrows(InvalidEntityDataException.class, () -> userService.addUser(user));
        assertEquals("Username " + user.getUsername() + " already registered", exception.getMessage());

        verify(mockUserRepository, times(1)).findByUsername(anyString());
        verifyNoMoreInteractions(mockUserRepository);

        verifyNoMoreInteractions(userValidator);
    }

//    @Test
//    void addUsersBatch() {
//    }
//
//    @Test
//    void updateUser() {
//    }
//
//    @Test
//    void deleteUserById() {
//    }
//
//    @Test
//    void count() {
//    }

    private User cloneUser(User u) {
        return new User(u.getFirstName(), u.getLastName(), u.getAge(), u.getUsername(), u.getPassword(), u.getRole(), u.getPhone(), u.isActive());
    }

    private User CREATED_USER = new User(1L, "John", "Doe", 42, "john", "John123#", Role.ADMIN,
            "+(1) 23424242323", true);
}
