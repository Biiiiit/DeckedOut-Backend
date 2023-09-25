package strategy_user_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.Exception.InvalidUserException;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Business.User.Impl.UpdateUserUseCaseImpl;
import strategy_card_game.Business.User.UpdateUserUseCase;
import strategy_card_game.Domain.User.*;
import strategy_card_game.Persistance.Impl.FakeUserRepositoryImpl;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserUseCaseImplTest {
    private GetUsersUseCase getUsersUseCase;
    private CreateUserUseCase createUserUseCase;
    private UpdateUserUseCase updateUserUseCase;
    private UserRepository fakeUserRepository;

    @BeforeEach
    public void setUp() {
        fakeUserRepository = new FakeUserRepositoryImpl();
        getUsersUseCase = new GetUsersUseCaseImpl(fakeUserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(fakeUserRepository);
        updateUserUseCase = new UpdateUserUseCaseImpl(fakeUserRepository);
    }

    @Test
    public void testUpdateUser() {
        CreateUserRequest userRequest = new CreateUserRequest(null, "User1", "email", "password", TypeOfUser.admin);
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
