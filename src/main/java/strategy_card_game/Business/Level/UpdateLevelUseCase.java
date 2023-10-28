package strategy_card_game.Business.Level;

import strategy_card_game.Domain.Level.UpdateLevelRequest;

public interface UpdateLevelUseCase {
    void updateLevel(UpdateLevelRequest request);
}
