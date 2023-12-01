package strategy_card_game.Controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.Area.*;
import strategy_card_game.Domain.Area.*;

import java.util.Optional;

@RestController
@RequestMapping("/areas")
@AllArgsConstructor
public class AreaController {
    private final GetAreaUseCase getAreaUseCase;
    private final GetAreasUseCase getAreasUseCase;
    private final DeleteAreaUseCase deleteAreaUseCase;
    private final CreateAreaUseCase createAreaUseCase;
    private final UpdateAreaUseCase updateAreaUseCase;

    @GetMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Area> getArea(@PathVariable(value = "id") final long id) {
        final Optional<Area> areaOptional = getAreaUseCase.getArea(id);
        if (areaOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(areaOptional.get());
    }
    @GetMapping
    @RolesAllowed({"admin"})
    public ResponseEntity<GetAllAreasResponse> getAllAreas() {
        GetAllAreasRequest request = GetAllAreasRequest.builder().build();
        GetAllAreasResponse response = getAreasUseCase.getAreas(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{areaId}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> deleteArea(@PathVariable int areaId) {
        deleteAreaUseCase.deleteArea(areaId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    @RolesAllowed({"admin"})
    public ResponseEntity<CreateAreaResponse> createArea(@RequestBody @Valid CreateAreaRequest request) {
        CreateAreaResponse response = createAreaUseCase.createArea(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("{id}")
    @RolesAllowed({"admin"})
    public ResponseEntity<Void> updateArea(@PathVariable("id") long id,
                                           @RequestBody @Valid UpdateAreaRequest request) {
        request.setId(id);
        updateAreaUseCase.updateArea(request);
        return ResponseEntity.noContent().build();
    }
}
