package strategy_card_game.Business.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Card.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.Card.impl.GetCardUseCaseImpl;
import strategy_card_game.Business.Card.impl.UpdateCardUseCaseImpl;
import strategy_card_game.Domain.Card.*;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCardUseCaseImplTest {

    @InjectMocks
    private UpdateCardUseCaseImpl updateCardUseCase;

    @InjectMocks
    private GetCardUseCaseImpl getCardUseCase;

    @InjectMocks
    private CreateCardUseCaseImpl createCardUseCase;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // Initialize the mocks and inject them into the use cases
        createCardUseCase = new CreateCardUseCaseImpl(cardRepository);
        updateCardUseCase = new UpdateCardUseCaseImpl(cardRepository);
        getCardUseCase = new GetCardUseCaseImpl(cardRepository);
    }

    @Test
    public void testUpdateCard() {
        // Create a mock CreateCardRequest
        CreateCardRequest request = new CreateCardRequest("CardName", TypeOfCard.Atk, 10, 0, 0);

        // Mock the behavior of cardRepository.save to return a CardEntity with an ID.
        CardEntity savedCard = new CardEntity(1L, "CardName", TypeOfCard.Atk, 10, 0, 0);
        when(cardRepository.save(any(CardEntity.class))).thenReturn(savedCard);

        // Call the createCardUseCase to create the card
        CreateCardResponse createResponse = createCardUseCase.createCard(request);

        // Mock the behavior of cardRepository.findById to return the created card
        when(cardRepository.findById(eq(createResponse.getCardId()))).thenReturn(Optional.of(savedCard));

        // Create an updated card
        UpdateCardRequest updatedCard = new UpdateCardRequest(createResponse.getCardId(), "UpdatedCard", TypeOfCard.Shield, 20, 5, 3);

        // Call the updateCard method
        updateCardUseCase.updateCard(updatedCard);

        // Verify that the card has been updated
        Optional<Card> optionalCard = getCardUseCase.getCard(createResponse.getCardId());
        assertTrue(optionalCard.isPresent());
        Card card = optionalCard.get();
        assertEquals("UpdatedCard", card.getName());
        assertEquals(TypeOfCard.Shield, card.getTypeOfCard());
        assertEquals(20, card.getDamage());
        assertEquals(5, card.getHealing());
        assertEquals(3, card.getShielding());
    }
}
