package strategy_card_game.Business.Level.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Level.GetLevelUseCase;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.LevelRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetLevelUseCaseImpl implements GetLevelUseCase {
    private final LevelRepository levelRepository;

    @Override
    public Optional<Level> getLevel(long levelID) {
        return levelRepository.findById(levelID).map(LevelConverter::convertToLevel);
    }
}
