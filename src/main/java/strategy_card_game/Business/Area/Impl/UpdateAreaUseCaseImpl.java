package strategy_card_game.Business.Area.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.Exception.InvalidAreaException;
import strategy_card_game.Business.Area.UpdateAreaUseCase;
import strategy_card_game.Business.Level.Impl.LevelConverter;
import strategy_card_game.Domain.Area.UpdateAreaRequest;
import strategy_card_game.Persistance.AreaRepository;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UpdateAreaUseCaseImpl implements UpdateAreaUseCase {
    private final AreaRepository areaRepository;

    @Override
    public void updateArea(UpdateAreaRequest request) {
        Optional<AreaEntity> areaOptional = areaRepository.findById(request.getId());
        if (areaOptional.isEmpty()) {
            throw new InvalidAreaException("LEVEL_ID_INVALID");
        }

        AreaEntity area = areaOptional.get();
        updateFields(request, area);
    }

    private void updateFields(UpdateAreaRequest request, AreaEntity area) {
        area.setName(request.getName());
        area.setDescription(request.getDescription());
        List<LevelEntity> levelEntities = request.getListOfLevels().stream()
                .map(LevelConverter::convertToLevelEntity)
                .collect(Collectors.toList());
        area.setListOfLevels(levelEntities);
        area.setBackgroundSprite(request.getBackgroundSprite());

        areaRepository.save(area);
    }
}
