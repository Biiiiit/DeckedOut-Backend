package strategy_card_game.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Card.CreateCardUseCase;
import strategy_card_game.Business.Card.impl.GetCardUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCardUseCaseImplTest {

    @InjectMocks
    private GetCardUseCaseImpl getCardUseCase;

    @Mock
    private CreateCardUseCase createCardUseCase;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of getCardUseCase here since Mockito takes care of it.
    }

    @Test
    public void testGetCard() {
        // Create a mock CardEntity to be returned by the repository
        CardEntity cardEntity = new CardEntity(1L, "Card1", TypeOfCard.Atk, 10, 0, 0);

        // Mock the behavior of cardRepository.findById to return the CardEntity when called with 1L
        when(cardRepository.findById(1L)).thenReturn(Optional.of(cardEntity));

        // Get the created card using getCardUseCase
        Optional<Card> optionalCard = getCardUseCase.getCard(1L);

        // Verify that the card is present and has the correct details
        assertTrue(optionalCard.isPresent());
        Card card = optionalCard.get();
        assertEquals("Card1", card.getName());
        assertEquals(TypeOfCard.Atk, card.getTypeOfCard());
        assertEquals(10, card.getDamage());
        assertEquals(0, card.getHealing());
        assertEquals(0, card.getShielding());
    }

    @Test
    public void testGetNonExistentCard() {
        // Attempt to get a card with an invalid ID
        when(cardRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Card> optionalCard = getCardUseCase.getCard(999L);

        // Verify that the optional is empty as the card doesn't exist
        assertTrue(optionalCard.isEmpty());
    }
}
