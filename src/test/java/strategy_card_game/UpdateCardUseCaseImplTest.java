package strategy_card_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.CreateCardUseCase;
import strategy_card_game.Business.Exception.InvalidCardException;
import strategy_card_game.Business.GetCardsUseCase;
import strategy_card_game.Business.UpdateCardUseCase;
import strategy_card_game.Business.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.impl.GetCardsUseCaseImpl;
import strategy_card_game.Business.impl.UpdateCardUseCaseImpl;
import strategy_card_game.Domain.*;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Impl.FakeCardRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateCardUseCaseImplTest {

    private GetCardsUseCase getCardsUseCase;
    private CreateCardUseCase createCardUseCase;
    private UpdateCardUseCase updateCardUseCase;
    private CardRepository fakeCardRepository;

    @BeforeEach
    public void setUp() {
        fakeCardRepository = new FakeCardRepositoryImpl(); // Instantiate your fake repository
        getCardsUseCase = new GetCardsUseCaseImpl(fakeCardRepository);
        createCardUseCase = new CreateCardUseCaseImpl(fakeCardRepository);
        updateCardUseCase = new UpdateCardUseCaseImpl(fakeCardRepository);
    }

    @Test
    public void testUpdateCard() {
        // Create a card using createCardUseCase
        CreateCardRequest cardRequest = new CreateCardRequest(null, "Card1", Type.Atk, 10, 0, 0);
        CreateCardResponse createResponse = createCardUseCase.createCard(cardRequest);

        // Get all cards and verify that the card is present
        List<Card> cardsBeforeUpdate = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();
        assertTrue(cardsBeforeUpdate.stream().anyMatch(card -> card.getId().equals(createResponse.getCardId())));

        // Update the card
        UpdateCardRequest updateRequest = new UpdateCardRequest(createResponse.getCardId(), "UpdatedCard", Type.Shield, 5, 2, 1);
        updateCardUseCase.updateCard(updateRequest);

        // Get all cards again and verify that the card is updated
        List<Card> cardsAfterUpdate = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();
        assertTrue(cardsAfterUpdate.stream().anyMatch(card -> card.getId().equals(createResponse.getCardId())));
        Card updatedCard = cardsAfterUpdate.stream()
                .filter(card -> card.getId().equals(createResponse.getCardId()))
                .findFirst()
                .orElse(null);

        assertEquals("UpdatedCard", updatedCard.getName());
        assertEquals(Type.Shield, updatedCard.getType());
        assertEquals(5, updatedCard.getDamage());
        assertEquals(2, updatedCard.getHealing());
        assertEquals(1, updatedCard.getShielding());
    }

    @Test
    public void testUpdateInvalidCard() {
        // Attempt to update a card with an invalid ID
        UpdateCardRequest updateRequest = new UpdateCardRequest(999L, "UpdatedCard", Type.Shield, 5, 2, 1);

        // Ensure that an InvalidCardException is thrown
        assertThrows(InvalidCardException.class, () -> updateCardUseCase.updateCard(updateRequest));
    }
}
