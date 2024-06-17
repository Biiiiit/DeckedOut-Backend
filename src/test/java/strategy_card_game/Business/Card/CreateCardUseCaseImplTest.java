package strategy_card_game.Business.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Card.Exception.CardAlreadyExistsException;
import strategy_card_game.Business.Card.impl.CreateCardUseCaseImpl;
import strategy_card_game.Domain.Card.CreateCardRequest;
import strategy_card_game.Domain.Card.CreateCardResponse;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCardUseCaseImplTest {

    @InjectMocks
    private CreateCardUseCaseImpl createCardUseCase;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of createCardUseCase here since Mockito takes care of it.
    }

    @Test
    public void testCreateCardSuccess() {
        // Create a mock CreateCardRequest
        CreateCardRequest request = new CreateCardRequest("CardName", TypeOfCard.Atk, 10, 0, 0);

        // Mock the behavior of cardRepository.save to return a CardEntity with an ID.
        CardEntity savedCard = new CardEntity(1L, "CardName", TypeOfCard.Atk, 10, 0, 0);
        when(cardRepository.save(Mockito.any(CardEntity.class))).thenReturn(savedCard);

        // Call the createCard method
        CreateCardResponse response = createCardUseCase.createCard(request);

        // Verify that the response contains the cardId
        assertNotNull(response);
        assertNotNull(response.getCardId());
    }

    @Test
    public void testCreateCardFailureCardAlreadyExists() {
        // Mock the behavior of cardRepository.existsByName to return true.
        when(cardRepository.existsByName("ExistingCardName")).thenReturn(true);

        // Create a CreateCardRequest for an existing card
        CreateCardRequest request = new CreateCardRequest("ExistingCardName", TypeOfCard.Atk, 10, 0, 0);

        // Call the createCard method and expect a CardAlreadyExistsException
        assertThrows(CardAlreadyExistsException.class, () -> createCardUseCase.createCard(request));
    }
}
