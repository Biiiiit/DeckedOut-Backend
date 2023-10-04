package strategy_card_game.Domain.Enemy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Enemy {
    private Long id;
    private String name;
    private Integer health;
    private Integer atk;
    private Integer healing;
    private Integer shielding;
    private String pattern;
    private Image sprite;
}
