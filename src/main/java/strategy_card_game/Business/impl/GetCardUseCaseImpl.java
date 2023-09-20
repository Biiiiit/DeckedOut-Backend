package strategy_card_game.Business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.GetCardUseCase;
import strategy_card_game.Domain.Card;
import strategy_card_game.Persistance.CardRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetCardUseCaseImpl implements GetCardUseCase {

    private CardRepository cardRepository;
    @Override
    public Optional<Card> getCard(long cardID) {
        return cardRepository.findById(cardID).map(CardConverter::convert);
    }
}
