package strategy_card_game.Business.Level.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Level.DeleteLevelUseCase;
import strategy_card_game.Persistance.LevelRepository;

@Service
@AllArgsConstructor
public class DeleteLevelUseCaseImpl implements DeleteLevelUseCase {
    private final LevelRepository levelRepository;

    @Override
    public void deleteLevel(long levelId) {
        this.levelRepository.deleteById(levelId);
    }
}
