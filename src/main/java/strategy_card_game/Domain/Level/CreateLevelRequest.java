package strategy_card_game.Domain.Level;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CreateLevelRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private List<Enemy> enemies;
    @NotNull
    private byte[] lvlSprite;
    @NotNull
    private byte[] backgroundSprite;
}