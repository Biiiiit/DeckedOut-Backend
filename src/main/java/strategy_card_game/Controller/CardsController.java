package strategy_card_game.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.*;
import strategy_card_game.Domain.*;

import java.util.Optional;

@RestController
@RequestMapping("/cards")
@AllArgsConstructor
public class CardsController {
    private final GetCardUseCase getCardUseCase;
    private final GetCardsUseCase getCardsUseCase;
    private final DeleteCardUseCase deleteCardUseCase;
    private final CreateCardUseCase createCardUseCase;
    private final UpdateCardUseCase updateCardUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Card> getCard(@PathVariable(value = "id") final long id) {
        final Optional<Card> cardOptional = getCardUseCase.getCard(id);
        if (cardOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cardOptional.get());
    }
    @GetMapping
    public ResponseEntity<GetAllCardsResponse> getAllCards() {
        GetAllCardsRequest request = GetAllCardsRequest.builder().build();
        GetAllCardsResponse response = getCardsUseCase.getCards(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable int cardId) {
        deleteCardUseCase.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateCardResponse> createCard(@RequestBody @Valid CreateCardRequest request) {
        CreateCardResponse response = createCardUseCase.createCard(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCard(@PathVariable("id") long id,
                                              @RequestBody @Valid UpdateCardRequest request) {
        request.setId(id);
        updateCardUseCase.updateCard(request);
        return ResponseEntity.noContent().build();
    }
}
