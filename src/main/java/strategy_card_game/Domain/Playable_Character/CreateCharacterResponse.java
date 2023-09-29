package strategy_card_game.Domain.Playable_Character;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCharacterResponse {
    private Long characterId;
}
