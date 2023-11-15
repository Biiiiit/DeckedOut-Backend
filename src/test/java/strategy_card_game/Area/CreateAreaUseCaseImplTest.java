package strategy_card_game.Area;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Area.Exception.AreaAlreadyExistsException;
import strategy_card_game.Business.Area.Impl.CreateAreaUseCaseImpl;
import strategy_card_game.Domain.Area.CreateAreaRequest;
import strategy_card_game.Domain.Area.CreateAreaResponse;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.AreaRepository;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateAreaUseCaseImplTest {
    @InjectMocks
    private CreateAreaUseCaseImpl createAreaUseCase;

    @Mock
    private AreaRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of createAreaUseCase here since Mockito takes care of it.
    }

    @Test
    public void testCreateAreaSuccess() {
        // Create a mock CreateAreaRequest
        CreateAreaRequest request = new CreateAreaRequest(1L, "AreaName", "AreaDescription", new ArrayList<>(), new byte[0]);

        // Mock the behavior of cardRepository.save to return a AreaEntity with an ID.
        AreaEntity savedArea = new AreaEntity(1L, "AreaName", "AreaDescription", new ArrayList<>(), new byte[0]);
        when(cardRepository.save(Mockito.any(AreaEntity.class))).thenReturn(savedArea);

        // Call the createArea method
        CreateAreaResponse response = createAreaUseCase.createArea(request);

        // Verify that the response contains the cardId
        assertNotNull(response);
    }

    @Test
    public void testCreateAreaFailureAreaAlreadyExists() {
        // Mock the behavior of cardRepository.existsByName to return true.
        when(cardRepository.existsByName("ExistingAreaName")).thenReturn(true);

        // Create a CreateAreaRequest for an existing card
        CreateAreaRequest request = new CreateAreaRequest(1L, "ExistingAreaName", "AreaDescription", new ArrayList<>(), new byte[0]);

        // Call the createArea method and expect a AreaAlreadyExistsException
        assertThrows(AreaAlreadyExistsException.class, () -> createAreaUseCase.createArea(request));
    }
}