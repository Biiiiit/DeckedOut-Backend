package strategy_card_game.Domain.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private long userId;
}
