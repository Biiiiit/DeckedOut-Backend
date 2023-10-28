package strategy_card_game.Business.Level;

import strategy_card_game.Domain.Level.GetAllLevelsRequest;
import strategy_card_game.Domain.Level.GetAllLevelsResponse;

public interface GetLevelsUseCase {
    GetAllLevelsResponse getLevels(GetAllLevelsRequest request);
}
