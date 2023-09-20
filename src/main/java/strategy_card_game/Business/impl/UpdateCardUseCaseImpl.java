package strategy_card_game.Business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Exception.InvalidCardException;
import strategy_card_game.Business.UpdateCardUseCase;
import strategy_card_game.Domain.UpdateCardRequest;
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
            throw new InvalidCardException("CARD_ID_INVALID");
        }

        CardEntity student = cardOptional.get();
        updateFields(request, student);
    }

    private void updateFields(UpdateCardRequest request, CardEntity card) {
        card.setName(request.getName());
        card.setType(request.getType());
        card.setDamage(request.getDamage());
        card.setHealing(request.getHealing());
        card.setShielding(request.getShielding());

        cardRepository.save(card);
    }
}
