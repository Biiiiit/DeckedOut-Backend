package strategy_card_game.Domain.Level;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Enemy.Enemy;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    private Long id;
    private String name;
    private List<Enemy> enemies;
    private byte[] lvlSprite;
    private byte[] backgroundSprite;
}
