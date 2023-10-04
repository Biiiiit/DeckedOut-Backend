package strategy_card_game.Persistance.Entity;

import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Builder
@Data
public class EnemyEntity {
    private Long id;
    private String name;
    private Integer health;
    private Integer atk;
    private Integer healing;
    private Integer shielding;
    private String pattern;
    private Image sprite;

    public EnemyEntity(Long id, String name, Integer health, Integer atk, Integer healing, Integer shielding, String pattern, Image sprite) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.atk = atk;
        this.healing = healing;
        this.shielding = shielding;
        this.pattern = pattern;
        this.sprite = sprite;
    }
}
