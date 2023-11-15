package strategy_card_game.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import strategy_card_game.Business.User.*;
import strategy_card_game.Domain.User.*;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private GetUserUseCase getUserUseCase;

    @Mock
    private GetUsersUseCase getUsersUseCase;

    @Mock
    private DeleteUserUseCase deleteUserUseCase;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private UpdateUserUseCase updateUserUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        userController = new UserController(getUserUseCase, getUsersUseCase, deleteUserUseCase, createUserUseCase, updateUserUseCase);
    }

    @Test
    void getUser_shouldReturnUserWhenFound() throws Exception {
        long userId = 1L;
        User mockUser = new User();
        mockUser.setId(1L);

        when(getUserUseCase.getUser(userId)).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(getUserUseCase, times(1)).getUser(userId);
        verifyNoMoreInteractions(getUserUseCase);
    }

    @Test
    void getUser_shouldReturnNotFoundWhenUserNotFound() throws Exception {
        long userId = 1L;

        when(getUserUseCase.getUser(userId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/{id}", userId))
                .andExpect(status().isNotFound());

        verify(getUserUseCase, times(1)).getUser(userId);
        verifyNoMoreInteractions(getUserUseCase);
    }

    @Test
    void getAllUsers_shouldReturnAllUsers() throws Exception {
        GetAllUsersResponse mockResponse = new GetAllUsersResponse(Collections.emptyList());

        when(getUsersUseCase.getUsers(any())).thenReturn(mockResponse);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.users").isArray());

        verify(getUsersUseCase, times(1)).getUsers(any());
        verifyNoMoreInteractions(getUsersUseCase);
    }

    @Test
    void deleteUser_shouldReturnNoContent() throws Exception {
        int userId = 1;

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isNoContent());

        verify(deleteUserUseCase, times(1)).deleteUser(userId);
        verifyNoMoreInteractions(deleteUserUseCase);
    }

    @Test
    void createUser_shouldReturnCreated() throws Exception {
        CreateUserRequest mockRequest = new CreateUserRequest();
        mockRequest.setId(42L); // Assuming setId method is available and sets the id

        // Mocking the createUserUseCase
        when(createUserUseCase.createUser(mockRequest)).thenAnswer(invocation -> {
            CreateUserRequest requestArgument = invocation.getArgument(0, CreateUserRequest.class);
            Long id = requestArgument.getId();

            // Ensure that id is not null
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null");
            }

            // Use reflection to set the id in the private constructor
            try {
                Constructor<CreateUserResponse> constructor = CreateUserResponse.class.getDeclaredConstructor(long.class);
                constructor.setAccessible(true);
                CreateUserResponse response = constructor.newInstance(id);
                return response;
            } catch (Exception e) {
                throw new RuntimeException("Error creating CreateUserResponse", e);
            }
        });

        ResponseEntity<CreateUserResponse> response = userController.createUser(mockRequest);

        verify(createUserUseCase, times(1)).createUser(mockRequest);
        verifyNoMoreInteractions(createUserUseCase);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateUser_shouldReturnNoContent() throws Exception {
        long userId = 1L;
        UpdateUserRequest mockRequest = new UpdateUserRequest();
        mockRequest.setId(1L);
        mockRequest.setUsername("UpdatedUsername");
        mockRequest.setEmail("test@gmail.com");
        mockRequest.setPassword("test");
        mockRequest.setType(TypeOfUser.valueOf("admin"));
        // Set other fields as needed

        mockMvc.perform(put("/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"username\": \"UpdatedUsername\", \"email\": \"test@gmail.com\", \"password\": \"test\", \"type\": \"admin\"}"))
                .andExpect(status().isNoContent());

        verify(updateUserUseCase, times(1)).updateUser(mockRequest);
        verifyNoMoreInteractions(updateUserUseCase);
    }
}
