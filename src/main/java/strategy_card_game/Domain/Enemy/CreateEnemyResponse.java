package strategy_card_game.Domain.Enemy;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateEnemyResponse {
    private long enemyId;
}
