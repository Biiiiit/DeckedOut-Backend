package strategy_card_game.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.DeleteUserUseCase;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.DeleteUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Domain.User.*;
import strategy_card_game.Persistance.Impl.FakeUserRepositoryImpl;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteUserUseCaseImplTest {
    private GetUsersUseCase getUsersUseCase;
    private CreateUserUseCase createUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    private UserRepository fakeUserRepository;

    @BeforeEach
    public void setUp() {
        fakeUserRepository = new FakeUserRepositoryImpl(); // Instantiate your fake repository
        getUsersUseCase = new GetUsersUseCaseImpl(fakeUserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(fakeUserRepository);
        deleteUserUseCase = new DeleteUserUseCaseImpl(fakeUserRepository);
    }

    @Test
    public void testDeleteUser() {
        // Create a user using createUserUseCase
        CreateUserRequest userRequest = new CreateUserRequest(null, "User1", "email", "password", "admin");
        CreateUserResponse createResponse = createUserUseCase.createUser(userRequest);

        // Get all users and verify that the user is present
        List<User> usersBeforeDelete = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();
        assertTrue(usersBeforeDelete.stream().anyMatch(user -> user.getId().equals(createResponse.getUserId())));

        // Delete the user
        deleteUserUseCase.deleteUser(createResponse.getUserId());

        // Get all users again and verify that the user is no longer present
        List<User> usersAfterDelete = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();
        assertFalse(usersAfterDelete.stream().anyMatch(user -> user.getId().equals(createResponse.getUserId())));
    }
}
