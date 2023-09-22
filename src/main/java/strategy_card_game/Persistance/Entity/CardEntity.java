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

    public CardEntity(Long id, String name, Type type, Integer damage, Integer healing, Integer shielding) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.healing = healing;
        this.shielding = shielding;
    }
}



