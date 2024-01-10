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
public class GetAllGamesRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private byte[] icon;
    @NotNull
    private byte [] banner;
    @NotBlank
    private List<Area> gameArea;
    @NotBlank
    private List<Card> gameCards;
    @NotBlank
    private List<Enemy> gameEnemies;
    @NotBlank
    private List<Level> gameLevels;
    @NotBlank
    private List<PlayableCharacter> gameCharacters;
    @NotNull
    private User developer;
}
