package strategy_card_game.Persistance.Entity;

import lombok.Builder;
import lombok.Data;
import strategy_card_game.Domain.Card.Card;

import java.awt.*;
import java.util.List;

@Builder
@Data
public class CharacterEntity {
    private Long id;
    private String name;
    private String description;
    private Integer health;
    private Integer ammo;
    private List<Card> startingDeck;
    private Image sprite;

    public CharacterEntity(Long id, String name, String description, Integer health, Integer ammo, List<Card> startingDeck, Image sprite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.health = health;
        this.ammo = ammo;
        this.startingDeck = startingDeck;
        this.sprite = sprite;
    }
}
