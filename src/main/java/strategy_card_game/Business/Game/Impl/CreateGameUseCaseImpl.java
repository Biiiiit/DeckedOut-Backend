package strategy_card_game.Business.Game.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.Impl.AreaConverter;
import strategy_card_game.Business.Card.impl.CardConverter;
import strategy_card_game.Business.Enemy.Impl.EnemyConverter;
import strategy_card_game.Business.Game.CreateGameUseCase;
import strategy_card_game.Business.Game.Exception.GameAlreadyExistsException;
import strategy_card_game.Business.Level.Impl.LevelConverter;
import strategy_card_game.Business.Playable_Character.Impl.CharacterConverter;
import strategy_card_game.Domain.Game.CreateGameRequest;
import strategy_card_game.Domain.Game.CreateGameResponse;
import strategy_card_game.Persistance.Entity.*;
import strategy_card_game.Persistance.GameRepository;
import strategy_card_game.Persistance.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateGameUseCaseImpl implements CreateGameUseCase {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    @Override
    public CreateGameResponse createGame(CreateGameRequest request){
        if (gameRepository.existsByName(request.getName())){
            throw new GameAlreadyExistsException();
        }

        GameEntity savedGame = saveNewGame(request);

        return CreateGameResponse.builder()
                .gameId(savedGame.getId())
                .build();
    }
    private GameEntity saveNewGame(CreateGameRequest request) {
        List<CardEntity> cards = (request.getGameCards() != null)
                ? request.getGameCards().stream().map(CardConverter::convertToCardEntity).collect(Collectors.toList())
                : Collections.emptyList();

        List<AreaEntity> areas = (request.getGameAreas() != null)
                ? request.getGameAreas().stream().map(AreaConverter::convertToAreaEntity).collect(Collectors.toList())
                : Collections.emptyList();

        List<EnemyEntity> enemies = (request.getGameEnemies() != null)
                ? request.getGameEnemies().stream().map(EnemyConverter::convertToEnemyEntity).collect(Collectors.toList())
                : Collections.emptyList();

        List<LevelEntity> levels = (request.getGameLevels() != null)
                ? request.getGameLevels().stream().map(LevelConverter::convertToLevelEntity).collect(Collectors.toList())
                : Collections.emptyList();

        List<CharacterEntity> characters = (request.getGameCharacters() != null)
                ? request.getGameCharacters().stream().map(CharacterConverter::convertToCharacterEntity).collect(Collectors.toList())
                : Collections.emptyList();

        GameEntity newGame = GameEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .icon(request.getIcon())
                .banner(request.getBanner())
                .gameAreas(areas)
                .gameCards(cards)
                .gameEnemies(enemies)
                .gameLevels(levels)
                .gameCharacters(characters)
                .developer(userRepository.findById(request.getDeveloper()).get())
                .build();

        return gameRepository.save(newGame);
    }
}
