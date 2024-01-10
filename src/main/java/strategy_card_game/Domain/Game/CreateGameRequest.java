package strategy_card_game.Domain.Game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Domain.User.User;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private byte[] icon;
    private byte [] banner;
    private List<Area> gameAreas;
    private List<Card> gameCards;
    private List<Enemy> gameEnemies;
    private List<Level> gameLevels;
    private List<PlayableCharacter> gameCharacters;
    private Long developer;
}
