package strategy_card_game.Business.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Domain.User.GetAllUsersRequest;
import strategy_card_game.Domain.User.TypeOfUser;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetUsersUseCaseImplTest {
    @InjectMocks
    private GetUsersUseCaseImpl getUsersUseCase;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of getUsersUseCase here since Mockito takes care of it.
    }

    @Test
    public void testGetAllUsers() {
        byte[] avatar =  new byte[0];
        // Create mock UserEntities to be returned by the repository
        UserEntity userEntity1 = new UserEntity(1L, "User1", "email1", "password1", TypeOfUser.admin, avatar);
        UserEntity userEntity2 = new UserEntity(2L, "User2", "email2", "password2", TypeOfUser.admin, avatar);

        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(userEntity1);
        userEntities.add(userEntity2);

        // Mock the behavior of userRepository.findAll to return the list of UserEntities
        when(userRepository.findAll()).thenReturn(userEntities);

        // Call the getUsers method
        List<User> users = getUsersUseCase.getUsers(new GetAllUsersRequest()).getUsers();

        // Verify the response
        assertEquals(2, users.size());

        // You can further assert the properties of the retrieved users if needed
        assertEquals("User1", users.get(0).getUsername());
        assertEquals("User2", users.get(1).getUsername());
    }
}
