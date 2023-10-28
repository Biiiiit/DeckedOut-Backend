package strategy_card_game.Domain.Playable_Character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Card.Card;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayableCharacter {
    private Long id;
    private String name;
    private String description;
    private Integer health;
    private Integer ammo;
    private List<Card> startingDeck;
    private byte[] sprite;
}
