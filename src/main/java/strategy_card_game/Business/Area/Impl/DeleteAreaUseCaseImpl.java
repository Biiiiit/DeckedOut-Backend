package strategy_card_game.Business.Area.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.DeleteAreaUseCase;
import strategy_card_game.Persistance.AreaRepository;

@Service
@AllArgsConstructor
public class DeleteAreaUseCaseImpl implements DeleteAreaUseCase {
    private final AreaRepository areaRepository;

    @Override
    public void deleteArea(long areaId) {
        this.areaRepository.deleteById(areaId);
    }
}
