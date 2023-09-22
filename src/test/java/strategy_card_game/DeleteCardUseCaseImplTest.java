package strategy_card_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.CreateCardUseCase;
import strategy_card_game.Business.DeleteCardUseCase;
import strategy_card_game.Business.GetCardsUseCase;
import strategy_card_game.Business.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.impl.DeleteCardUseCaseImpl;
import strategy_card_game.Business.impl.GetCardsUseCaseImpl;
import strategy_card_game.Domain.*;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Impl.FakeCardRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteCardUseCaseImplTest {

    private GetCardsUseCase getCardsUseCase;
    private CreateCardUseCase createCardUseCase;
    private DeleteCardUseCase deleteCardUseCase;
    private CardRepository fakeCardRepository;

    @BeforeEach
    public void setUp() {
        fakeCardRepository = new FakeCardRepositoryImpl(); // Instantiate your fake repository
        getCardsUseCase = new GetCardsUseCaseImpl(fakeCardRepository);
        createCardUseCase = new CreateCardUseCaseImpl(fakeCardRepository);
        deleteCardUseCase = new DeleteCardUseCaseImpl(fakeCardRepository);
    }

    @Test
    public void testDeleteCard() {
        // Create a card using createCardUseCase
        CreateCardRequest cardRequest = new CreateCardRequest(null, "Card1", Type.Atk, 10, 0, 0);
        CreateCardResponse createResponse = createCardUseCase.createCard(cardRequest);

        // Get all cards and verify that the card is present
        List<Card> cardsBeforeDelete = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();
        assertTrue(cardsBeforeDelete.stream().anyMatch(card -> card.getId().equals(createResponse.getCardId())));

        // Delete the card
        deleteCardUseCase.deleteCard(createResponse.getCardId());

        // Get all cards again and verify that the card is no longer present
        List<Card> cardsAfterDelete = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();
        assertFalse(cardsAfterDelete.stream().anyMatch(card -> card.getId().equals(createResponse.getCardId())));
    }
}
