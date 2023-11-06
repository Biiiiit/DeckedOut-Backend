package strategy_card_game.Business.Area.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.GetAreasUseCase;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Area.GetAllAreasRequest;
import strategy_card_game.Domain.Area.GetAllAreasResponse;
import strategy_card_game.Persistance.AreaRepository;
import strategy_card_game.Persistance.Entity.AreaEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAreasUseCaseImpl implements GetAreasUseCase {
    private final AreaRepository areaRepository;

    @Override
    public GetAllAreasResponse getAreas(final GetAllAreasRequest request) {
        List<AreaEntity> results = areaRepository.findAll();

        final GetAllAreasResponse response = new GetAllAreasResponse();
        List<Area> areas = results
                .stream()
                .map(AreaConverter::convert)
                .toList();
        response.setAreas(areas);

        return response;
    }
}
