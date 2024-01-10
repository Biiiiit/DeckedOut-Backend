package strategy_card_game.Business.Game.Impl;

import strategy_card_game.Business.Area.Impl.AreaConverter;
import strategy_card_game.Business.Card.impl.CardConverter;
import strategy_card_game.Business.Enemy.Impl.EnemyConverter;
import strategy_card_game.Business.Level.Impl.LevelConverter;
import strategy_card_game.Business.Playable_Character.Impl.CharacterConverter;
import strategy_card_game.Business.User.Impl.UserConverter;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Enemy.Enemy;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.Entity.GameEntity;

import java.util.List;
import java.util.stream.Collectors;

public class GameConverter {
    private GameConverter() {
    }

    public static Game convert(GameEntity game) {
        List<Card> cards = game.getGameCards()
                .stream()
                .map(CardConverter::convertToCard)
                .collect(Collectors.toList());

        List<Area> areas = game.getGameAreas()
                .stream()
                .map(AreaConverter::convertToArea)
                .collect(Collectors.toList());

        List<Enemy> enemies = game.getGameEnemies()
                .stream()
                .map(EnemyConverter::convertToEnemy)
                .collect(Collectors.toList());

        List<Level> levels = game.getGameLevels()
                .stream()
                .map(LevelConverter::convertToLevel)
                .collect(Collectors.toList());

        List<PlayableCharacter> characters = game.getGameCharacters()
                .stream()
                .map(CharacterConverter::convertToCharacter)
                .collect(Collectors.toList());

        return Game.builder()
                .id(game.getId())
                .name(game.getName())
                .description(game.getDescription())
                .icon(game.getIcon())
                .banner(game.getBanner())
                .gameAreas(areas)
                .gameCards(cards)
                .gameEnemies(enemies)
                .gameLevels(levels)
                .gameCharacters(characters)
                .developer(UserConverter.convertToUser(game.getDeveloper()))
                .build();
    }
}
