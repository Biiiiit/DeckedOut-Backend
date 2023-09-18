package strategy_card_game.Persistance.Entity;

import lombok.Builder;
import lombok.Data;
import strategy_card_game.Domain.Type;

@Builder
@Data
public class CardEntity {
    private Long id;
    private String name;
    private Type type;
    private Integer damage;
    private Integer healing;
    private Integer shielding;
}
