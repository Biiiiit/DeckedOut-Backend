package strategy_card_game.Domain.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUsersRequest {
    @NotBlank
    private String username;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private TypeOfUser type;
    @NotNull
    private byte[] avatar;
}
