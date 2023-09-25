package strategy_card_game.Business.Card.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Card.DeleteCardUseCase;
import strategy_card_game.Persistance.CardRepository;

@Service
@AllArgsConstructor
public class DeleteCardUseCaseImpl implements DeleteCardUseCase {
    private final CardRepository cardRepository;

    @Override
    public void deleteCard(long cardId) {
        this.cardRepository.deleteById(cardId);
    }
}
