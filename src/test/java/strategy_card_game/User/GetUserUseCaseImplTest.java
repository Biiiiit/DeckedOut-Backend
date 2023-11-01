package strategy_card_game.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.GetUserUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUserUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetUserUseCaseImplTest {
    private GetUserUseCase getUserUseCase;
    private CreateUserUseCase createUserUseCase;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository UserRepository;

    @BeforeEach
    public void setUp() {
        getUserUseCase = new GetUserUseCaseImpl(UserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(UserRepository);
    }

    @Test
    public void testGetUser() {
        // Create a user using createUserUseCase
        CreateUserRequest userRequest = new CreateUserRequest(1L, "User1", "email", "password", "admin");
        CreateUserResponse createResponse = createUserUseCase.createUser(userRequest);

        // Get the created user using getUserUseCase
        Optional<User> optionalUser = getUserUseCase.getUser(createResponse.getUserId());

        // Verify that the user is present and has the correct details
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals("User1", user.getUsername());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(TypeOfUser.admin, user.getType());
    }

    @Test
    public void testGetNonExistentUser() {
        // Attempt to get a user with an invalid ID
        Optional<User> optionalUser = getUserUseCase.getUser(999L);

        // Verify that the optional is empty as the user doesn't exist
        assertTrue(optionalUser.isEmpty());
    }
}
