package strategy_card_game.Controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.Playable_Character.*;
import strategy_card_game.Domain.Playable_Character.*;

import java.util.Optional;

@RestController
@RequestMapping("/character")
@AllArgsConstructor
public class CharacterController {
    private final GetCharactersUseCase getCharactersUseCase;
    private final GetCharacterUseCase getCharacterUseCase;
    private final DeleteCharacterUseCase deleteCharacterUseCase;
    private final CreateCharacterUseCase createCharacterUseCase;
    private final UpdateCharacterUseCase updateCharacterUseCase;

    @GetMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<PlayableCharacter> getCharacter(@PathVariable(value = "id") final long id) {
        final Optional<PlayableCharacter> characterOptional = getCharacterUseCase.getCharacter(id);
        if (characterOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(characterOptional.get());
    }
    @GetMapping
    @RolesAllowed({"admin"})
    public ResponseEntity<GetAllCharactersResponse> getAllCharacters() {
        GetAllCharactersRequest request = GetAllCharactersRequest.builder().build();
        GetAllCharactersResponse response = getCharactersUseCase.getCharacters(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{characterId}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> deleteCharacter(@PathVariable int characterId) {
        deleteCharacterUseCase.deleteCharacter(characterId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @RolesAllowed({"admin"})
    public ResponseEntity<CreateCharacterResponse> createCharacter(@RequestBody @Valid CreateCharacterRequest request) {
        CreateCharacterResponse response = createCharacterUseCase.createCharacter(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> updateCharacter(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateCharacterRequest request) {
        request.setId(id);
        updateCharacterUseCase.updateCharacter(request);
        return ResponseEntity.noContent().build();
    }
}
