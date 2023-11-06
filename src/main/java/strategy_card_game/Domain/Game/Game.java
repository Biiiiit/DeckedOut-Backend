package strategy_card_game.Domain.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private long id;
    private String name;
    private String description;
    private byte[] icon;
    private byte [] banner;
    private List<Area> gameAreas;
    private List<Card> gameCards;
    private List<Enemy> gameEnemies;
    private List<Level> gameLevels;
    private List<PlayableCharacter> gameCharacters;
}
