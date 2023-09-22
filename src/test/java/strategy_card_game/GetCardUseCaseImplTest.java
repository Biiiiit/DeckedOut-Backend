package strategy_card_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.CreateCardUseCase;
import strategy_card_game.Business.GetCardUseCase;
import strategy_card_game.Business.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.impl.GetCardUseCaseImpl;
import strategy_card_game.Domain.Card;
import strategy_card_game.Domain.CreateCardRequest;
import strategy_card_game.Domain.CreateCardResponse;
import strategy_card_game.Domain.Type;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Impl.FakeCardRepositoryImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCardUseCaseImplTest {

    private GetCardUseCase getCardUseCase;
    private CreateCardUseCase createCardUseCase;
    private CardRepository fakeCardRepository;

    @BeforeEach
    public void setUp() {
        fakeCardRepository = new FakeCardRepositoryImpl(); // Instantiate your fake repository
        getCardUseCase = new GetCardUseCaseImpl(fakeCardRepository);
        createCardUseCase = new CreateCardUseCaseImpl(fakeCardRepository);
    }

    @Test
    public void testGetCard() {
        // Create a card using createCardUseCase
        CreateCardRequest cardRequest = new CreateCardRequest(null, "Card1", Type.Atk, 10, 0, 0);
        CreateCardResponse createResponse = createCardUseCase.createCard(cardRequest);

        // Get the created card using getCardUseCase
        Optional<Card> optionalCard = getCardUseCase.getCard(createResponse.getCardId());

        // Verify that the card is present and has the correct details
        assertTrue(optionalCard.isPresent());
        Card card = optionalCard.get();
        assertEquals("Card1", card.getName());
        assertEquals(Type.Atk, card.getType());
        assertEquals(10, card.getDamage());
        assertEquals(0, card.getHealing());
        assertEquals(0, card.getShielding());
    }

    @Test
    public void testGetNonExistentCard() {
        // Attempt to get a card with an invalid ID
        Optional<Card> optionalCard = getCardUseCase.getCard(999L);

        // Verify that the optional is empty as the card doesn't exist
        assertTrue(optionalCard.isEmpty());
    }
}