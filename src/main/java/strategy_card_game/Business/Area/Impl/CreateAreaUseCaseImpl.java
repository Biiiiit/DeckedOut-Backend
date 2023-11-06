package strategy_card_game.Business.Area.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Area.CreateAreaUseCase;
import strategy_card_game.Business.Area.Exception.AreaAlreadyExistsException;
import strategy_card_game.Business.Level.Impl.LevelConverter;
import strategy_card_game.Domain.Area.CreateAreaRequest;
import strategy_card_game.Domain.Area.CreateAreaResponse;
import strategy_card_game.Persistance.AreaRepository;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreateAreaUseCaseImpl implements CreateAreaUseCase {
    private final AreaRepository areaRepository;
    
    @Override
    public CreateAreaResponse createArea(CreateAreaRequest request){
        if (areaRepository.existsByName(request.getName())){
            throw new AreaAlreadyExistsException();
        }

        AreaEntity savedArea = saveNewArea(request);

        return CreateAreaResponse.builder()
                .areaId(savedArea.getId())
                .build();
    }
    private AreaEntity saveNewArea(CreateAreaRequest request) {
        List<LevelEntity> levelEntities = request.getListOfLevels().stream()
                .map(LevelConverter::convertToLevelEntity)
                .collect(Collectors.toList());

        AreaEntity newArea = AreaEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .listOfLevels(levelEntities)
                .backgroundSprite(request.getBackgroundSprite())
                .build();
        return areaRepository.save(newArea);
    }
}
