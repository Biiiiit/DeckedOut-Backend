package strategy_card_game.Business.Game.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Game.DeleteGameUseCase;
import strategy_card_game.Persistance.GameRepository;

@Service
@AllArgsConstructor
public class DeleteGameUseCaseImpl implements DeleteGameUseCase {
    private final GameRepository gameRepository;

    @Override
    public void deleteGame(long gameId) {
        this.gameRepository.deleteById(gameId);
    }
}
