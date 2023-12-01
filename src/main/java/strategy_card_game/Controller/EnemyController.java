package strategy_card_game.Controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.Enemy.*;
import strategy_card_game.Domain.Enemy.*;

import java.util.Optional;

@RestController
@RequestMapping("/enemies")
@AllArgsConstructor
public class EnemyController {
    private final GetEnemiesUseCase getEnemiesUseCase;
    private final GetEnemyUseCase getEnemyUseCase;
    private final DeleteEnemyUseCase deleteEnemyUseCase;
    private final CreateEnemyUseCase createEnemyUseCase;
    private final UpdateEnemyUseCase updateEnemyUseCase;

    @GetMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Enemy> getEnemy(@PathVariable(value = "id") final long id) {
        final Optional<Enemy> enemyOptional = getEnemyUseCase.getEnemy(id);
        if (enemyOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(enemyOptional.get());
    }
    @GetMapping
    @RolesAllowed({"admin"})
    public ResponseEntity<GetAllEnemyResponse> getAllEnemies() {
        GetAllEnemyRequest request = GetAllEnemyRequest.builder().build();
        GetAllEnemyResponse response = getEnemiesUseCase.getEnemies(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{enemyId}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> deleteEnemy(@PathVariable int enemyId) {
        deleteEnemyUseCase.deleteEnemy(enemyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @RolesAllowed({"admin"})
    public ResponseEntity<CreateEnemyResponse> createEnemy(@RequestBody @Valid CreateEnemyRequest request) {
        CreateEnemyResponse response = createEnemyUseCase.createEnemy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> updateEnemy(@PathVariable("id") long id,
                                                @RequestBody @Valid UpdateEnemyRequest request) {
        request.setId(id);
        updateEnemyUseCase.updateEnemy(request);
        return ResponseEntity.noContent().build();
    }
}
