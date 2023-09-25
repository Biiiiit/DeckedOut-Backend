package strategy_card_game.Business.Card.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Card.UpdateCardUseCase;
import strategy_card_game.Business.User.Exception.InvalidUserException;
import strategy_card_game.Domain.Card.UpdateCardRequest;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateCardUseCaseImpl implements UpdateCardUseCase {
    private final CardRepository cardRepository;

    @Override
    public void updateCard(UpdateCardRequest request) {
        Optional<CardEntity> cardOptional = cardRepository.findById(request.getId());
        if (cardOptional.isEmpty()) {
            throw new InvalidUserException("CARD_ID_INVALID");
        }

        CardEntity card = cardOptional.get();
        updateFields(request, card);
    }

    private void updateFields(UpdateCardRequest request, CardEntity card) {
        card.setName(request.getName());
        card.setTypeOfCard(request.getTypeOfCard());
        card.setDamage(request.getDamage());
        card.setHealing(request.getHealing());
        card.setShielding(request.getShielding());

        cardRepository.save(card);
    }
}
