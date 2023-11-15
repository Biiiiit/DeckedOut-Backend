package strategy_card_game.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import strategy_card_game.Business.Card.*;
import strategy_card_game.Domain.Card.*;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@WebMvcTest(CardsController.class)
class CardsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetCardUseCase getCardUseCase;

    @MockBean
    private GetCardsUseCase getCardsUseCase;

    @MockBean
    private DeleteCardUseCase deleteCardUseCase;

    @MockBean
    private CreateCardUseCase createCardUseCase;

    @MockBean
    private UpdateCardUseCase updateCardUseCase;
    @InjectMocks
    private CardsController cardsController;
    @BeforeEach
    void setUp() {

        // Mock the createCardUseCase
        createCardUseCase = mock(CreateCardUseCase.class);

        // Initialize the controller with the mocked createCardUseCase
        cardsController = new CardsController(getCardUseCase, getCardsUseCase, deleteCardUseCase, createCardUseCase,updateCardUseCase);
    }

    @Test
    void getCard_shouldReturnCardWhenFound() throws Exception {
        long cardId = 1L;
        Card mockCard = new Card();
        mockCard.setId(1L);

        when(getCardUseCase.getCard(cardId)).thenReturn(Optional.of(mockCard));

        mockMvc.perform(get("/cards/{id}", cardId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));

        verify(getCardUseCase, times(1)).getCard(cardId);
        verifyNoMoreInteractions(getCardUseCase);
    }


    @Test
    void getCard_shouldReturnNotFoundWhenCardNotFound() throws Exception {
        long cardId = 1L;

        when(getCardUseCase.getCard(cardId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cards/{id}", cardId))
                .andExpect(status().isNotFound());

        verify(getCardUseCase, times(1)).getCard(cardId);
        verifyNoMoreInteractions(getCardUseCase);
    }

    @Test
    void getAllCards_shouldReturnAllCards() throws Exception {
        GetAllCardsResponse mockResponse = new GetAllCardsResponse(Collections.emptyList());

        when(getCardsUseCase.getCards(any())).thenReturn(mockResponse);

        mockMvc.perform(get("/cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cards").isArray());

        verify(getCardsUseCase, times(1)).getCards(any());
        verifyNoMoreInteractions(getCardsUseCase);
    }

    @Test
    void deleteCard_shouldReturnNoContent() throws Exception {
        int cardId = 1;

        mockMvc.perform(delete("/cards/{cardId}", cardId))
                .andExpect(status().isNoContent());

        verify(deleteCardUseCase, times(1)).deleteCard(cardId);
        verifyNoMoreInteractions(deleteCardUseCase);
    }

    @Test
    void createCard_shouldReturnCreated() throws Exception {
        CreateCardRequest mockRequest = new CreateCardRequest(); // Replace with actual request

        // Mocking the createCardUseCase
        when(createCardUseCase.createCard(mockRequest)).thenAnswer(invocation -> {
            Long id = invocation.getArgument(0, CreateCardRequest.class).getId(); // Assuming getId() returns Long

            // Use reflection to set the id in the private constructor
            try {
                Constructor<CreateCardResponse> constructor = CreateCardResponse.class.getDeclaredConstructor(Long.class);
                constructor.setAccessible(true);
                CreateCardResponse response = constructor.newInstance(id);
                return response;
            } catch (Exception e) {
                throw new RuntimeException("Error creating CreateCardResponse", e);
            }
        });

        ResponseEntity<CreateCardResponse> response = cardsController.createCard(mockRequest);

        verify(createCardUseCase, times(1)).createCard(mockRequest);
        verifyNoMoreInteractions(createCardUseCase);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void updateCard_shouldReturnNoContent() throws Exception {
        long cardId = 1L;
        UpdateCardRequest mockRequest = new UpdateCardRequest();
        mockRequest.setId(1L);
        mockRequest.setName("Card2");
        mockRequest.setTypeOfCard(TypeOfCard.valueOf("Atk"));
        mockRequest.setDamage(10);
        mockRequest.setHealing(0);
        mockRequest.setShielding(0);

        mockMvc.perform(put("/cards/{id}", cardId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Card2\", \"typeOfCard\": \"Atk\", \"damage\": 10, \"healing\": 0, \"shielding\": 0}"))
                .andExpect(status().isNoContent());

        verify(updateCardUseCase, times(1)).updateCard(mockRequest);
        verifyNoMoreInteractions(updateCardUseCase);
    }

}