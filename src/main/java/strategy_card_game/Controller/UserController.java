package strategy_card_game.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import strategy_card_game.Business.User.*;
import strategy_card_game.Domain.User.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    private final GetUserUseCase getUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("{identifier}")
    //@RolesAllowed({"normal", "admin"})
    public ResponseEntity<User> getUserByIdOrUsername(@PathVariable(value = "identifier") String identifier) {
        try {
            long userId = Long.parseLong(identifier);
            // If it's a valid long, assume it's an ID
            Optional<User> userOptional = getUserUseCase.getUser(userId);
            return userOptional.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (NumberFormatException e) {
            // If it's not a valid long, assume it's a username
            Optional<User> userOptional = getUserUseCase.getUserByUsername(identifier);
            return userOptional.map(user -> ResponseEntity.ok().body(user)).orElseGet(() -> ResponseEntity.notFound().build());
        }
    }
    @GetMapping
    public ResponseEntity<GetAllUsersResponse> getAllUsers() {
        GetAllUsersRequest request = GetAllUsersRequest.builder().build();
        GetAllUsersResponse response = getUsersUseCase.getUsers(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        deleteUserUseCase.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") long id,
            @Valid @ModelAttribute UpdateUserRequest updatedUser
    ) {
        updatedUser.setId(id);

        updateUserUseCase.updateUser(updatedUser);
        return ResponseEntity.noContent().build();
    }
}
