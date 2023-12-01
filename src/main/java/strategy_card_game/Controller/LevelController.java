package strategy_card_game.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.Level.*;
import strategy_card_game.Domain.Level.*;

import java.util.Optional;

@RestController
@RequestMapping("/level")
@AllArgsConstructor
public class LevelController {
    private final GetLevelsUseCase getLevelsUseCase;
    private final GetLevelUseCase getLevelUseCase;
    private final DeleteLevelUseCase deleteLevelUseCase;
    private final CreateLevelUseCase createLevelUseCase;
    private final UpdateLevelUseCase updateLevelUseCase;

    @GetMapping("{id}")
    public ResponseEntity<Level> getLevel(@PathVariable(value = "id") final long id) {
        final Optional<Level> levelOptional = getLevelUseCase.getLevel(id);
        if (levelOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(levelOptional.get());
    }
    @GetMapping
    public ResponseEntity<GetAllLevelsResponse> getAllLevels() {
        GetAllLevelsRequest request = GetAllLevelsRequest.builder().build();
        GetAllLevelsResponse response = getLevelsUseCase.getLevels(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{levelId}")
    public ResponseEntity<Void> deleteLevel(@PathVariable int levelId) {
        deleteLevelUseCase.deleteLevel(levelId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<CreateLevelResponse> createLevel(@RequestBody @Valid CreateLevelRequest request) {
        CreateLevelResponse response = createLevelUseCase.createLevel(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateLevel(@PathVariable("id") long id,
                                                @RequestBody @Valid UpdateLevelRequest request) {
        request.setId(id);
        updateLevelUseCase.updateLevel(request);
        return ResponseEntity.noContent().build();
    }
}
