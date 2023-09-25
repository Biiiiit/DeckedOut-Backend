package strategy_card_game.Domain.Card;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCardResponse {
    private Long cardId;
}
