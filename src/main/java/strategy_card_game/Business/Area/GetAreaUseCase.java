package strategy_card_game.Business.Area;

import strategy_card_game.Domain.Area.Area;

import java.util.Optional;

public interface GetAreaUseCase {
    Optional<Area> getArea(long areaId);
}
