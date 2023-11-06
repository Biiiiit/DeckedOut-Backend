package strategy_card_game.Business.Area.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.GetAreaUseCase;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Persistance.AreaRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetAreaUseCaseImpl implements GetAreaUseCase {
    private final AreaRepository areaRepository;

    @Override
    public Optional<Area> getArea(long areaID) {
        return areaRepository.findById(areaID).map(AreaConverter::convert);
    }
}
