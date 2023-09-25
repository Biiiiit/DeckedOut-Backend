package strategy_card_game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Card.CreateCardUseCase;
import strategy_card_game.Business.Card.GetCardsUseCase;
import strategy_card_game.Business.Card.impl.CreateCardUseCaseImpl;
import strategy_card_game.Business.Card.impl.GetCardsUseCaseImpl;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.CreateCardRequest;
import strategy_card_game.Domain.Card.GetAllCardsRequest;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Impl.FakeCardRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCardsUseCaseImplTest {

    private GetCardsUseCase getCardsUseCase;
    private CreateCardUseCase createCardUseCase;
    private CardRepository fakeCardRepository;

    @BeforeEach
    public void setUp() {
        fakeCardRepository = new FakeCardRepositoryImpl(); // Instantiate your fake repository
        getCardsUseCase = new GetCardsUseCaseImpl(fakeCardRepository);
        createCardUseCase = new CreateCardUseCaseImpl(fakeCardRepository);
    }

    @Test
    public void testGetAllCards() {
        // Create two cards using createCardUseCase
        CreateCardRequest card1Request = new CreateCardRequest(null, "Card1", TypeOfCard.Atk, 10, 0, 0);
        CreateCardRequest card2Request = new CreateCardRequest(null, "Card2", TypeOfCard.Shield, 0, 0, 4);

        createCardUseCase.createCard(card1Request);
        createCardUseCase.createCard(card2Request);

        // Call the getCards method
        List<Card> cards = getCardsUseCase.getCards(new GetAllCardsRequest()).getCards();

        // Verify the response
        assertEquals(2, cards.size());

        // You can further assert the properties of the retrieved cards if needed
        assertEquals("Card1", cards.get(0).getName());
        assertEquals("Card2", cards.get(1).getName());
    }
}
