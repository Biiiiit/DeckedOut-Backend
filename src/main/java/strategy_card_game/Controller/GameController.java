package strategy_card_game.Controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.Game.*;
import strategy_card_game.Domain.Game.*;

import java.util.Optional;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController {
    private final GetGameUseCase getGameUseCase;
    private final GetGamesUseCase getGamesUseCase;
    private final DeleteGameUseCase deleteGameUseCase;
    private final CreateGameUseCase createGameUseCase;
    private final UpdateGameUseCase updateGameUseCase;

    @GetMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Game> getGame(@PathVariable(value = "id") final long id) {
        final Optional<Game> gameOptional = getGameUseCase.getGame(id);
        if (gameOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(gameOptional.get());
    }
    @GetMapping
    @RolesAllowed({"admin"})
    public ResponseEntity<GetAllGamesResponse> getAllGames() {
        GetAllGamesRequest request = GetAllGamesRequest.builder().build();
        GetAllGamesResponse response = getGamesUseCase.getGames(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{gameId}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> deleteGame(@PathVariable int gameId) {
        deleteGameUseCase.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @RolesAllowed({"admin"})
    public ResponseEntity<CreateGameResponse> createGame(@RequestBody @Valid CreateGameRequest request) {
        CreateGameResponse response = createGameUseCase.createGame(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> updateGame(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateGameRequest request) {
        request.setId(id);
        updateGameUseCase.updateGame(request);
        return ResponseEntity.noContent().build();
    }
}
