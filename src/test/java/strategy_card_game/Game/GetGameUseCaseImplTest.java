package strategy_card_game.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Game.Impl.CreateGameUseCaseImpl;
import strategy_card_game.Business.Game.Impl.GetGameUseCaseImpl;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Persistance.Entity.*;
import strategy_card_game.Persistance.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetGameUseCaseImplTest {
    @InjectMocks
    private GetGameUseCaseImpl getGameUseCase;

    @Mock
    private CreateGameUseCaseImpl createGameUseCase;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        byte[] Image = new byte[0];
        List<EnemyEntity> enemies = new ArrayList<>();
        List<AreaEntity> areas = new ArrayList<>();
        List<CardEntity> cards = new ArrayList<>();
        List<CharacterEntity> characters = new ArrayList<>();
        List<LevelEntity> levels = new ArrayList<>();
        GameEntity gameEntity = new GameEntity(1L, "GameName", "Description", Image, Image, areas, cards, enemies, levels, characters);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(gameEntity));

        Optional<Game> optionalGame = getGameUseCase.getGame(1L);

        assertTrue(optionalGame.isPresent());
        Game game = optionalGame.get();
        assertEquals("GameName", game.getName());
        assertEquals("Description", game.getDescription());
        assertEquals(Image, game.getBanner());
        assertEquals(Image, game.getIcon());
        assertEquals(areas, game.getGameAreas());
        assertEquals(cards, game.getGameCards());
        assertEquals(enemies, game.getGameEnemies());
        assertEquals(levels, game.getGameLevels());
        assertEquals(characters, game.getGameCharacters());
    }
}
