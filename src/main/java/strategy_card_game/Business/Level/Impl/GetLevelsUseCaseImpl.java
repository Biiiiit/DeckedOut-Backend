package strategy_card_game.Business.Level.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Level.GetLevelsUseCase;
import strategy_card_game.Domain.Level.GetAllLevelsRequest;
import strategy_card_game.Domain.Level.GetAllLevelsResponse;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.Entity.LevelEntity;
import strategy_card_game.Persistance.LevelRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetLevelsUseCaseImpl implements GetLevelsUseCase {
    private final LevelRepository levelRepository;

    @Override
    public GetAllLevelsResponse getLevels(final GetAllLevelsRequest request) {
        List<LevelEntity> results = levelRepository.findAll();

        final GetAllLevelsResponse response = new GetAllLevelsResponse();
        List<Level> levels = results
                .stream()
                .map(LevelConverter::convertToLevel)
                .toList();
        response.setLevels(levels);

        return response;
    }
}
