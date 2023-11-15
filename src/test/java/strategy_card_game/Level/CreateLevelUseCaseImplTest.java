package strategy_card_game.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Level.Exception.LevelAlreadyExistsException;
import strategy_card_game.Business.Level.Impl.CreateLevelUseCaseImpl;
import strategy_card_game.Domain.Level.CreateLevelRequest;
import strategy_card_game.Domain.Level.CreateLevelResponse;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateLevelUseCaseImplTest {
    @InjectMocks
    private CreateLevelUseCaseImpl createLevelUseCase;

    @Mock
    private LevelRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of createLevelUseCase here since Mockito takes care of it.
    }

    @Test
    public void testCreateLevelSuccess() {
        // Create a mock CreateLevelRequest
        CreateLevelRequest request = new CreateLevelRequest(1L, "LevelName", new ArrayList<>(), new byte[0], new byte[0]);

        // Mock the behavior of cardRepository.save to return a LevelEntity with an ID.
        LevelEntity savedLevel = new LevelEntity(1L, "LevelName", new ArrayList<>(), new byte[0], new byte[0]);
        when(cardRepository.save(Mockito.any(LevelEntity.class))).thenReturn(savedLevel);

        // Call the createLevel method
        CreateLevelResponse response = createLevelUseCase.createLevel(request);

        // Verify that the response contains the cardId
        assertNotNull(response);
    }

    @Test
    public void testCreateLevelFailureLevelAlreadyExists() {
        // Mock the behavior of cardRepository.existsByName to return true.
        when(cardRepository.existsByName("ExistingLevelName")).thenReturn(true);

        // Create a CreateLevelRequest for an existing card
        CreateLevelRequest request = new CreateLevelRequest(1L, "ExistingLevelName", new ArrayList<>(), new byte[0], new byte[0]);

        // Call the createLevel method and expect a LevelAlreadyExistsException
        assertThrows(LevelAlreadyExistsException.class, () -> createLevelUseCase.createLevel(request));
    }
}