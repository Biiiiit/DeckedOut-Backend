package strategy_card_game.Domain.Level;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateLevelResponse {
    private long levelId;
}
