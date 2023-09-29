package strategy_card_game.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.User.Exception.UserAlreadyExistsException;
import strategy_card_game.Business.User.Impl.CreateUserUseCaseImpl;
import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.Impl.FakeUserRepositoryImpl;
import strategy_card_game.Persistance.UserRepository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateUserUseCaseImplTest {
    private CreateUserUseCaseImpl createUserUseCase;
    private UserRepository fakeUserRepository;

    @BeforeEach
    public void setUp() {
        fakeUserRepository = new FakeUserRepositoryImpl(); // Instantiate your fake repository
        createUserUseCase = new CreateUserUseCaseImpl(fakeUserRepository);
    }

    @Test
    public void testCreateUserSuccess() {
        CreateUserRequest request = new CreateUserRequest(1L,"Username", "email", "password", TypeOfUser.admin);

        // Call the createCard method
        CreateUserResponse response = createUserUseCase.createUser(request);

        // Verify that the card was saved and response contains the cardId
        assertNotNull(response);
        assertNotNull(response.getUserId());
    }

    @Test
    public void testCreateUserFailureUserAlreadyExists() {
        // Create a card in the fake repository
        UserEntity existingUser = new UserEntity(null, "ExistingUsername", "email", "password", TypeOfUser.admin);
        fakeUserRepository.save(existingUser);

        // Create a CreateCardRequest for an existing card
        CreateUserRequest request = new CreateUserRequest(1L, "ExistingUsername", "email", "password", TypeOfUser.admin);

        // Call the createCard method and expect a CardAlreadyExistsException
        assertThrows(UserAlreadyExistsException.class, () -> createUserUseCase.createUser(request));
    }
}
