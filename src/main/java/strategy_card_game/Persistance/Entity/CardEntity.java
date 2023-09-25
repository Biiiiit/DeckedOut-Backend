package strategy_card_game.Persistance.Entity;

import lombok.Builder;
import lombok.Data;
import strategy_card_game.Domain.Card.TypeOfCard;

@Builder
@Data
public class CardEntity {
    private Long id;
    private String name;
    private TypeOfCard typeOfCard;
    private Integer damage;
    private Integer healing;
    private Integer shielding;

    public CardEntity(Long id, String name, TypeOfCard typeOfCard, Integer damage, Integer healing, Integer shielding) {
        this.id = id;
        this.name = name;
        this.typeOfCard = typeOfCard;
        this.damage = damage;
        this.healing = healing;
        this.shielding = shielding;
    }
}



