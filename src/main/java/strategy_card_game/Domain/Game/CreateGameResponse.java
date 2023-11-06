package strategy_card_game.Domain.Game;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateGameResponse {
    private long gameId;
}
