package strategy_card_game.Business.Card.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Business.Card.CreateCardUseCase;
import strategy_card_game.Business.Card.Exception.CardAlreadyExistsException;
import strategy_card_game.Domain.Card.CreateCardRequest;
import strategy_card_game.Domain.Card.CreateCardResponse;
import strategy_card_game.Persistance.Entity.CardEntity;

@Service
@AllArgsConstructor
public class CreateCardUseCaseImpl implements CreateCardUseCase {
    private final CardRepository cardRepository;

    @Override
    @Transactional
    public CreateCardResponse createCard(CreateCardRequest request){
        if (cardRepository.existsByName(request.getName())){
            throw new CardAlreadyExistsException();
        }

        CardEntity savedCard = saveNewCard(request);

        return CreateCardResponse.builder()
                .cardId(savedCard.getId())
                .build();
    }
    private CardEntity saveNewCard(CreateCardRequest request) {

        CardEntity newCard = CardEntity.builder()
                .id(request.getId())
                .name(request.getName())
                .typeOfCard(request.getTypeOfCard())
                .damage(request.getDamage())
                .healing(request.getHealing())
                .shielding(request.getShielding())
                .build();
        return cardRepository.save(newCard);
    }
}
