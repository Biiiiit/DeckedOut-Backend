package strategy_card_game.Business.Game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Game.Impl.GetGamesUseCaseImpl;
import strategy_card_game.Domain.Game.GetAllGamesRequest;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.GameEntity;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.GameRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetGamesUseCaseImplTest {
    @InjectMocks
    private GetGamesUseCaseImpl getGamesUseCase;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    public void setUp() {
        // No need to create instances here since Mockito takes care of them.
    }

    @Test
    public void testGetAllCards() {
        User developer = new User();
        UserEntity developer2 = new UserEntity();
        byte[] Image = new byte[0];

        GameEntity gameEntity1 = new GameEntity(1L, "Game1", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), developer2);
        GameEntity gameEntity2 = new GameEntity(2L, "Game2", "Description", new byte[0], new byte[0], new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), developer2);

        List<GameEntity> gameList = new ArrayList<>();
        gameList.add(gameEntity1);
        gameList.add(gameEntity2);

        when(gameRepository.findAll()).thenReturn(gameList);

        List<Game> games = getGamesUseCase.getGames(new GetAllGamesRequest()).getGames();

        assertEquals(2, games.size());

        assertEquals("Game1", games.get(0).getName());
        assertEquals("Game2", games.get(1).getName());
    }
}
