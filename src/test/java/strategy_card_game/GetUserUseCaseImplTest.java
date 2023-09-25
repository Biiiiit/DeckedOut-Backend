package strategy_user_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.GetUserUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUserUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Impl.FakeUserRepositoryImpl;
import strategy_card_game.Persistance.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetUserUseCaseImplTest {
    private GetUserUseCase getUserUseCase;
    private CreateUserUseCase createUserUseCase;
    private UserRepository fakeUserRepository;

    @BeforeEach
    public void setUp() {
        fakeUserRepository = new FakeUserRepositoryImpl(); // Instantiate your fake repository
        getUserUseCase = new GetUserUseCaseImpl(fakeUserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(fakeUserRepository);
    }

    @Test
    public void testGetUser() {
        // Create a user using createUserUseCase
        CreateUserRequest userRequest = new CreateUserRequest(null, "User1", "email", "password", TypeOfUser.admin);
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
