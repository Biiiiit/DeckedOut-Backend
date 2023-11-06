package strategy_card_game.Business.Area;

import strategy_card_game.Domain.Area.GetAllAreasRequest;
import strategy_card_game.Domain.Area.GetAllAreasResponse;

public interface GetAreasUseCase {
    GetAllAreasResponse getAreas(GetAllAreasRequest request);
}
