package strategy_card_game.Business.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import strategy_card_game.Business.Card.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.Card.impl.DeleteCardUseCaseImpl;
import strategy_card_game.Business.Card.impl.GetCardUseCaseImpl;
import strategy_card_game.Domain.Card.CreateCardRequest;
import strategy_card_game.Domain.Card.CreateCardResponse;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class DeleteCardUseCaseImplTest {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private GetCardUseCaseImpl getCardUseCase;

    @InjectMocks
    private CreateCardUseCaseImpl createCardUseCase;

    @InjectMocks
    private DeleteCardUseCaseImpl deleteCardUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks
    }

    @Test
    public void testCreateAndDeleteCard() {
        // Create a card using createCardUseCase
        CreateCardRequest cardRequest = new CreateCardRequest("Card1", TypeOfCard.Atk, 10, 0, 0);

        // Mock the behavior of cardRepository.save to return a CardEntity with an ID.
        CardEntity savedCard = new CardEntity(1L, "Card1", TypeOfCard.Atk, 10, 0, 0);
        when(cardRepository.save(Mockito.any(CardEntity.class))).thenReturn(savedCard);

        CreateCardResponse createResponse = createCardUseCase.createCard(cardRequest);

        // Verify that the card is initially present
        assertTrue(getCardUseCase.getCard(createResponse.getCardId()).isEmpty());

        // Delete the card
        deleteCardUseCase.deleteCard(createResponse.getCardId());

        // Verify that the card is no longer present after deletion
        assertTrue(getCardUseCase.getCard(createResponse.getCardId()).isEmpty());
    }
}
