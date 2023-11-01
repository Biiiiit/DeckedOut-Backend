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
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.GetAllUsersRequest;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GetUsersUseCaseImplTest {
    private GetUsersUseCase getUsersUseCase;
    private CreateUserUseCase createUserUseCase;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository UserRepository;

    @BeforeEach
    public void setUp() {
        getUsersUseCase = new GetUsersUseCaseImpl(UserRepository);
        createUserUseCase = new CreateUserUseCaseImpl(UserRepository);
    }

    @Test
    public void testGetAllUsers() {
        CreateUserRequest user1Request = new CreateUserRequest(1L, "User1", "email", "password", "admin");
        CreateUserRequest user2Request = new CreateUserRequest(2L, "User2", "email", "password", "admin");

        createUserUseCase.createUser(user1Request);
        createUserUseCase.createUser(user2Request);

        List<User> users = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();

        assertEquals(2, users.size());

        assertEquals("User1", users.get(0).getUsername());
        assertEquals("User2", users.get(1).getUsername());
    }
}
