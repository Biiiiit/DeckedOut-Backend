package strategy_card_game.Business.Game.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.Impl.AreaConverter;
import strategy_card_game.Business.Game.GetGameUseCase;
import strategy_card_game.Domain.Game.Game;
import strategy_card_game.Persistance.GameRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetGameUseCaseImpl implements GetGameUseCase {
    private final GameRepository gameRepository;

    @Override
    public Optional<Game> getGame(long areaID) {
        return gameRepository.findById(areaID).map(GameConverter::convert);
    }
}
