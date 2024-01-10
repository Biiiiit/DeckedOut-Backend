package strategy_card_game.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.Game.*;
import strategy_card_game.Business.User.GetUserUseCase;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Business.User.Impl.GetUsersUseCaseImpl;
import strategy_card_game.Domain.Game.*;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController {
    private final GetGameUseCase getGameUseCase;
    private final GetGamesUseCase getGamesUseCase;
    private final GetGamesByDeveloper getGamesByDeveloper;
    private final DeleteGameUseCase deleteGameUseCase;
    private final CreateGameUseCase createGameUseCase;
    private final UpdateGameUseCase updateGameUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Game> getGame(@PathVariable(value = "id") final long id) {
        final Optional<Game> gameOptional = getGameUseCase.getGame(id);
        if (gameOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(gameOptional.get());
    }
    @RolesAllowed("admin")
    @GetMapping("developer/{developerID}")
    public ResponseEntity<GetAllGamesResponse> getGameByDeveloperId(@PathVariable(value = "developerID") final long developerID) {
        final GetDeveloperGamesRequest request = new GetDeveloperGamesRequest(developerID);
        final GetAllGamesResponse developerGames = getGamesByDeveloper.getGamesByDeveloper(request);

        if (developerGames.getGames().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(developerGames);
    }

    @GetMapping
    public ResponseEntity<GetAllGamesResponse> getAllGames() {
        GetAllGamesRequest request = GetAllGamesRequest.builder().build();
        GetAllGamesResponse response = getGamesUseCase.getGames(request);
        return ResponseEntity.ok(response);
    }
    @RolesAllowed("admin")
    @DeleteMapping("{gameId}")
    public ResponseEntity<Void> deleteGame(@PathVariable int gameId) {
        deleteGameUseCase.deleteGame(gameId);
        return ResponseEntity.noContent().build();
    }

    @RolesAllowed("admin")
    @PostMapping()
    public ResponseEntity<CreateGameResponse> createGame(@RequestBody @Valid CreateGameRequest request) {
        CreateGameResponse response = createGameUseCase.createGame(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @RolesAllowed("admin")
    @PutMapping("{id}")
    public ResponseEntity<Void> updateGame(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateGameRequest request) {
        request.setId(id);
        updateGameUseCase.updateGame(request);
        return ResponseEntity.noContent().build();
    }
}
