package strategy_card_game.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import strategy_card_game.Business.User.Exception.UserAlreadyExistsException;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateUserUseCaseImplTest {
    private CreateUserUseCaseImpl createUserUseCase;
    @Qualifier("userRepository")
    @Autowired
    private UserRepository UserRepository;

    @BeforeEach
    public void setUp() {
        createUserUseCase = new CreateUserUseCaseImpl(UserRepository);
    }

    @Test
    public void testCreateUserSuccess() {
        CreateUserRequest request = new CreateUserRequest(1L,"Username", "email", "password", "admin");

        // Call the createCard method
        CreateUserResponse response = createUserUseCase.createUser(request);

        // Verify that the card was saved and response contains the cardId
        assertNotNull(response);
        assertNotNull(response.getUserId());
    }

    @Test
    public void testCreateUserFailureUserAlreadyExists() {
        // Create a card in the fake repository
        UserEntity existingUser = new UserEntity(1L, "ExistingUsername", "email", "password", TypeOfUser.admin);
        UserRepository.save(existingUser);

        // Create a CreateCardRequest for an existing card
        CreateUserRequest request = new CreateUserRequest(1L, "ExistingUsername", "email", "password", "admin");

        // Call the createCard method and expect a CardAlreadyExistsException
        assertThrows(UserAlreadyExistsException.class, () -> createUserUseCase.createUser(request));
    }
}
