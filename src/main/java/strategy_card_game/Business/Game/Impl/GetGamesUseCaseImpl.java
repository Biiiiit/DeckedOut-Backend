package strategy_card_game.Business.Game.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Game.GetGamesUseCase;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Domain.Game.GetAllGamesRequest;
import strategy_card_game.Domain.Game.GetAllGamesResponse;
import strategy_card_game.Persistance.Entity.GameEntity;
import strategy_card_game.Persistance.GameRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGamesUseCaseImpl implements GetGamesUseCase {
    private final GameRepository gameRepository;

    @Override
    public GetAllGamesResponse getGames(final GetAllGamesRequest request) {
        List<GameEntity> results = gameRepository.findAll();

        final GetAllGamesResponse response = new GetAllGamesResponse();
        List<Game> games = results
                .stream()
                .map(GameConverter::convert)
                .toList();
        response.setGames(games);

        return response;
    }
}
