package strategy_card_game.Business.Game.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Game.GetGamesByDeveloper;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Domain.Game.GetAllGamesResponse;
import strategy_card_game.Domain.Game.GetDeveloperGamesRequest;
import strategy_card_game.Persistance.Entity.GameEntity;
import strategy_card_game.Persistance.GameRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetGamesByDeveloperImpl implements GetGamesByDeveloper {
    private final GameRepository gameRepository;
    @Override
    public GetAllGamesResponse getGamesByDeveloper(GetDeveloperGamesRequest request) {
        List<GameEntity> results = gameRepository.findByDeveloper_Id(request.getId());

        final GetAllGamesResponse response = new GetAllGamesResponse();
        List<Game> games = results
                .stream()
                .map(GameConverter::convert)
                .toList();
        response.setGames(games);

        return response;
    }
}
