package strategy_card_game.Business.Game.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.Impl.AreaConverter;
import strategy_card_game.Business.Card.impl.CardConverter;
import strategy_card_game.Business.Enemy.Impl.EnemyConverter;
import strategy_card_game.Business.Game.Exception.InvalidGameException;
import strategy_card_game.Business.Game.UpdateGameUseCase;
import strategy_card_game.Business.Level.Impl.LevelConverter;
import strategy_card_game.Business.Playable_Character.Impl.CharacterConverter;
import strategy_card_game.Domain.Game.UpdateGameRequest;
import strategy_card_game.Persistance.Entity.*;
import strategy_card_game.Persistance.GameRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpdateGameUseCaseImpl implements UpdateGameUseCase {
    private final GameRepository gameRepository;

    @Override
    public void updateGame(UpdateGameRequest request) {
        Optional<GameEntity> gameOptional = gameRepository.findById(request.getId());
        if (gameOptional.isEmpty()) {
            throw new InvalidGameException("LEVEL_ID_INVALID");
        }

        GameEntity game = gameOptional.get();
        updateFields(request, game);
    }

    private void updateFields(UpdateGameRequest request, GameEntity game) {
        List<CardEntity> cards = request.getGameCards()
                .stream()
                .map(CardConverter::convertToCardEntity)
                .collect(Collectors.toList());

        List<AreaEntity> areas = request.getGameAreas()
                .stream()
                .map(AreaConverter::convertToAreaEntity)
                .collect(Collectors.toList());

        List<EnemyEntity> enemies = request.getGameEnemies()
                .stream()
                .map(EnemyConverter::convertToEnemyEntity)
                .collect(Collectors.toList());

        List<LevelEntity> levels = request.getGameLevels()
                .stream()
                .map(LevelConverter::convertToLevelEntity)
                .collect(Collectors.toList());

        List<CharacterEntity> characters = request.getGameCharacters()
                .stream()
                .map(CharacterConverter::convertToCharacterEntity)
                .collect(Collectors.toList());
        game.setName(request.getName());
        game.setDescription(request.getDescription());
        game.setIcon(request.getIcon());
        game.setBanner(request.getBanner());
        game.setGameAreas(areas);
        game.setGameCards(cards);
        game.setGameEnemies(enemies);
        game.setGameLevels(levels);
        game.setGameCharacters(characters);

        gameRepository.save(game);
    }
}
