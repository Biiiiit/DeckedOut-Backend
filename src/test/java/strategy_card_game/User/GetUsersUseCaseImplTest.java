package strategy_card_game.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.User.CreateUserUseCase;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.GetAllUsersRequest;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Impl.FakeUserRepositoryImpl;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUsersUseCaseImplTest {
    private GetUsersUseCase getUsersUseCase;
    private CreateUserUseCase createUserUseCase;
    private UserRepository fakeUserRepository;

    @BeforeEach
    public void setUp() {
        fakeUserRepository = new FakeUserRepositoryImpl();
        getUsersUseCase = new GetUsersUseCaseImpl(fakeUserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(fakeUserRepository);
    }

    @Test
    public void testGetAllUsers() {
        CreateUserRequest user1Request = new CreateUserRequest(null, "User1", "email", "password", "admin");
        CreateUserRequest user2Request = new CreateUserRequest(null, "User2", "email", "password", "admin");

        createUserUseCase.createUser(user1Request);
        createUserUseCase.createUser(user2Request);

        List<User> users = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();

        assertEquals(2, users.size());

        assertEquals("User1", users.get(0).getUsername());
        assertEquals("User2", users.get(1).getUsername());
    }
}
