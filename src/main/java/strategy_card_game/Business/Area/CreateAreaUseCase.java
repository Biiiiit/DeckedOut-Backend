package strategy_card_game.Business.Area;

import strategy_card_game.Domain.Area.CreateAreaRequest;
import strategy_card_game.Domain.Area.CreateAreaResponse;

public interface CreateAreaUseCase {
    CreateAreaResponse createArea(CreateAreaRequest request);
}
