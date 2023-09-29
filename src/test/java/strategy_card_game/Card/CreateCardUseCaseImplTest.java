package strategy_card_game.Card;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy_card_game.Business.Card.Exception.CardAlreadyExistsException;
import strategy_card_game.Business.Card.impl.CreateCardUseCaseImpl;
import strategy_card_game.Domain.Card.CreateCardRequest;
import strategy_card_game.Domain.Card.CreateCardResponse;
import strategy_card_game.Domain.Card.TypeOfCard;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;
import strategy_card_game.Persistance.Impl.FakeCardRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateCardUseCaseImplTest {

    private CreateCardUseCaseImpl createCardUseCase;
    private CardRepository fakeCardRepository;

    @BeforeEach
    public void setUp() {
        fakeCardRepository = new FakeCardRepositoryImpl(); // Instantiate your fake repository
        createCardUseCase = new CreateCardUseCaseImpl(fakeCardRepository);
    }

    @Test
    public void testCreateCardSuccess() {
        // Create a mock CreateCardRequest
        CreateCardRequest request = new CreateCardRequest(1L,"CardName", TypeOfCard.Atk, 10, 0, 0);

        // Call the createCard method
        CreateCardResponse response = createCardUseCase.createCard(request);

        // Verify that the card was saved and response contains the cardId
        assertNotNull(response);
        assertNotNull(response.getCardId());
    }

    @Test
    public void testCreateCardFailureCardAlreadyExists() {
        // Create a card in the fake repository
        CardEntity existingCard = new CardEntity(null, "ExistingCardName", TypeOfCard.Atk, 10, 0, 0);
        fakeCardRepository.save(existingCard);

        // Create a CreateCardRequest for an existing card
        CreateCardRequest request = new CreateCardRequest(1L, "ExistingCardName", TypeOfCard.Atk, 10, 0, 0);

        // Call the createCard method and expect a CardAlreadyExistsException
        assertThrows(CardAlreadyExistsException.class, () -> createCardUseCase.createCard(request));
    }
}
