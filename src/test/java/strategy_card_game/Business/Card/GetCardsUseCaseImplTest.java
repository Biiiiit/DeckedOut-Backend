package strategy_card_game.Business.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Card.impl.GetCardsUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.GetAllCardsRequest;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // Use MockitoExtension for JUnit 5
public class GetCardsUseCaseImplTest {

    @InjectMocks
    private GetCardsUseCaseImpl getCardsUseCase;

    @Mock
    private CardRepository cardRepository;

    @BeforeEach
    public void setUp() {
        // No need to create the instance of getCardsUseCase here since Mockito takes care of it.
    }

    @Test
    public void testGetAllCards() {
        // Create mock CardEntities to be returned by the repository
        CardEntity cardEntity1 = new CardEntity(1L, "Card1", TypeOfCard.Atk, 10, 0, 0);
        CardEntity cardEntity2 = new CardEntity(2L, "Card2", TypeOfCard.Shield, 0, 0, 4);

        List<CardEntity> cardEntities = new ArrayList<>();
        cardEntities.add(cardEntity1);
        cardEntities.add(cardEntity2);

        // Mock the behavior of cardRepository.findAll to return the list of CardEntities
        when(cardRepository.findAll()).thenReturn(cardEntities);

        // Call the getCards method
        List<Card> cards = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();

        // Verify the response
        assertEquals(2, cards.size());

        // You can further assert the properties of the retrieved cards if needed
        assertEquals("Card1", cards.get(0).getName());
        assertEquals("Card2", cards.get(1).getName());
    }
}
