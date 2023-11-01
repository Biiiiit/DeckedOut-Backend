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
import strategy_card_game.Business.User.DeleteUserUseCase;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.DeleteUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Domain.User.*;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeleteUserUseCaseImplTest {
    private GetUsersUseCase getUsersUseCase;
    private CreateUserUseCase createUserUseCase;
    private DeleteUserUseCase deleteUserUseCase;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository UserRepository;

    @BeforeEach
    public void setUp() {
        getUsersUseCase = new GetUsersUseCaseImpl(UserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(UserRepository);
        deleteUserUseCase = new DeleteUserUseCaseImpl(UserRepository);
    }

    @Test
    public void testDeleteUser() {
        // Create a user using createUserUseCase
        CreateUserRequest userRequest = new CreateUserRequest(1L, "User1", "email", "password", "admin");
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
