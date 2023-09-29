package strategy_card_game.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Card.CreateCardUseCase;
import strategy_card_game.Business.Card.Exception.InvalidCardException;
import strategy_card_game.Business.Card.GetCardsUseCase;
import strategy_card_game.Business.Card.UpdateCardUseCase;
import strategy_card_game.Business.Card.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.Card.impl.GetCardsUseCaseImpl;
import strategy_card_game.Business.Card.impl.UpdateCardUseCaseImpl;
import strategy_card_game.Domain.Card.*;
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
        fakeCardRepository = new FakeCardRepositoryImpl();
        getCardsUseCase = new GetCardsUseCaseImpl(fakeCardRepository);
        createCardUseCase = new CreateCardUseCaseImpl(fakeCardRepository);
        updateCardUseCase = new UpdateCardUseCaseImpl(fakeCardRepository);
    }

    @Test
    public void testUpdateCard() {
        CreateCardRequest cardRequest = new CreateCardRequest(null, "Card1", TypeOfCard.Atk, 10, 0, 0);
        CreateCardResponse createResponse = createCardUseCase.createCard(cardRequest);

        List<Card> cardsBeforeUpdate = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();
        assertTrue(cardsBeforeUpdate.stream().anyMatch(card -> card.getId().equals(createResponse.getCardId())));

        // Update the card
        UpdateCardRequest updateRequest = new UpdateCardRequest(createResponse.getCardId(), "UpdatedCard", TypeOfCard.Shield, 5, 2, 1);
        updateCardUseCase.updateCard(updateRequest);

        List<Card> cardsAfterUpdate = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();
        assertTrue(cardsAfterUpdate.stream().anyMatch(card -> card.getId().equals(createResponse.getCardId())));
        Card updatedCard = cardsAfterUpdate.stream()
                .filter(card -> card.getId().equals(createResponse.getCardId()))
                .findFirst()
                .orElse(null);

        assertEquals("UpdatedCard", updatedCard.getName());
        assertEquals(TypeOfCard.Shield, updatedCard.getTypeOfCard());
        assertEquals(5, updatedCard.getDamage());
        assertEquals(2, updatedCard.getHealing());
        assertEquals(1, updatedCard.getShielding());
    }

    @Test
    public void testUpdateInvalidCard() {
        UpdateCardRequest updateRequest = new UpdateCardRequest(999L, "UpdatedCard", TypeOfCard.Shield, 5, 2, 1);

        assertThrows(InvalidCardException.class, () -> updateCardUseCase.updateCard(updateRequest));
    }
}
