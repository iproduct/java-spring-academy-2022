package course.qa.spring.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import course.qa.spring.dao.UserJpaRepository;
import course.qa.spring.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import static course.qa.spring.model.Role.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
@ActiveProfiles("test")
class UsersControllerTest {

//    @LocalServerPort
//    private int port;

    @MockBean
    private UserJpaRepository userRepo;

    @Autowired
    private MockMvc mockMvc;


    ObjectMapper mapper = new ObjectMapper();;

    @BeforeEach
    void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUsers() throws Exception {
        // mock userRepo DB query
        given(userRepo.findAll()).willReturn(DEFAULT_USERS);

        var response = mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON));
        response
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(DEFAULT_USERS.size()))
                .andExpect(jsonPath("$[0]", hasEntry("username", EXPECTED_USERS.get(0).getUsername())))
                .andExpect(jsonPath("$[*].username",
                        hasItems(EXPECTED_USERS.stream().map(User::getUsername).toArray())));

        var body = response.andReturn().getResponse().getContentAsString();
        var userList = mapper.readValue(body, new TypeReference<List<User>>() {
        });

        assertThat(userList).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(EXPECTED_USERS);

        // optionally verfy repo interactions
        verify(userRepo, times(1)).findAll();
        verifyNoMoreInteractions(userRepo);
    }

    @Test
    void addUser() throws Exception {
        // mock userRepo DB query
        given(userRepo.findByUsername(NEW_USER.getUsername())).willReturn(Optional.empty());
        given(userRepo.save(NEW_USER)).willReturn(CREATED_USER);

        // call http handler
        var response = mockMvc.perform(post("/api/users").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(NEW_USER)));

        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", Matchers.endsWith("/api/users/" + CREATED_USER.getId())))
                .andExpect(jsonPath("$.id").value(CREATED_USER.getId()));

        var body = response.andReturn().getResponse().getContentAsString();
        var user = mapper.readValue(body, User.class);

        assertThat(user).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(NEW_USER);

        // optionally verfy repo interactions
        verify(userRepo, times(1)).findByUsername(anyString());
        verify(userRepo, times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepo);
    }

    public static final List<User> DEFAULT_USERS = List.of(
            new User(1L, "Default", "Admin", 20, "admin", "Admin123#", ADMIN,
                    "+(359) 889654532", true),
            new User(2L, "John", "Doe", 42, "john", "John123#", READER,
                    "+(1) 23424242323", true),
            new User(3L, "Jane", "Doe", 34, "jane", "Jane123#", AUTHOR,
                    "+(359) 889654532", true)
    );

    public static User NEW_USER = new User("New", "User", 34, "newuser", "NewUser123#", AUTHOR,
            "+(359) 53453434", true);
    public static User CREATED_USER = new User(1L, "New", "User", 34, "newuser", "NewUser123#", AUTHOR,
            "+(359) 53453434", true);
    public static final List<User> EXPECTED_USERS = List.of(
            new User(1L, "Default", "Admin", 20, "admin", "Admin123#", ADMIN,
                    "+(359) 889654532", true),
            new User("John", "Doe", 42, "john", "John123#", READER,
                    "+(1) 23424242323", true),
            new User("Jane", "Doe", 34, "jane", "Jane123#", AUTHOR,
                    "+(359) 889654532", true)
    );
}
