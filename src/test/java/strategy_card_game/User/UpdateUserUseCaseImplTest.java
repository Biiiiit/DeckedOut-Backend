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
import strategy_card_game.Business.User.Exception.InvalidUserException;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Business.User.Impl.UpdateUserUseCaseImpl;
import strategy_card_game.Business.User.UpdateUserUseCase;
import strategy_card_game.Domain.User.*;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UpdateUserUseCaseImplTest {
    private GetUsersUseCase getUsersUseCase;
    private CreateUserUseCase createUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository UserRepository;

    @BeforeEach
    public void setUp() {
        getUsersUseCase = new GetUsersUseCaseImpl(UserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(UserRepository);
        updateUserUseCase = new UpdateUserUseCaseImpl(UserRepository);
    }

    @Test
    public void testUpdateUser() {
        CreateUserRequest userRequest = new CreateUserRequest(1L, "User1", "email", "password", "admin");
        CreateUserResponse createResponse = createUserUseCase.createUser(userRequest);

        List<User> usersBeforeUpdate = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();
        assertTrue(usersBeforeUpdate.stream().anyMatch(user -> user.getId().equals(createResponse.getUserId())));

        UpdateUserRequest updateRequest = new UpdateUserRequest(createResponse.getUserId(), "UpdatedUser", "email", "password", TypeOfUser.normal);
        updateUserUseCase.updateUser(updateRequest);

        List<User> usersAfterUpdate = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();
        assertTrue(usersAfterUpdate.stream().anyMatch(user -> user.getId().equals(createResponse.getUserId())));
        User updatedUser = usersAfterUpdate.stream()
                .filter(user -> user.getId().equals(createResponse.getUserId()))
                .findFirst()
                .orElse(null);

        assertEquals("UpdatedUser", updatedUser.getUsername());
        assertEquals("email", updatedUser.getEmail());
        assertEquals("password", updatedUser.getPassword());
        assertEquals(TypeOfUser.normal, updatedUser.getType());
    }

    @Test
    public void testUpdateInvalidUser() {
        UpdateUserRequest updateRequest = new UpdateUserRequest(999L, "UpdatedUser", "email", "password", TypeOfUser.normal);

        assertThrows(InvalidUserException.class, () -> updateUserUseCase.updateUser(updateRequest));
    }
}
