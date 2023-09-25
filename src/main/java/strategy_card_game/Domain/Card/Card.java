package strategy_card_game.Domain.Card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Long id;
    private String name;
    private TypeOfCard typeOfCard;
    private Integer damage;
    private Integer healing;
    private Integer shielding;
}
