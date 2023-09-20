package strategy_card_game.Business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import strategy_card_game.Business.GetCardsUseCase;
import strategy_card_game.Domain.Card;
import strategy_card_game.Domain.GetAllCardsRequest;
import strategy_card_game.Domain.GetAllCardsResponse;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCardsUseCaseImpl implements GetCardsUseCase {
    private CardRepository cardRepository;

    @Override
    public GetAllCardsResponse getCards(final GetAllCardsRequest request) {
        List<CardEntity> results;
        if (StringUtils.hasText(String.valueOf(request.getType()))) {
            results = cardRepository.findAllByType(String.valueOf(request.getType()));
        } else {
            results = cardRepository.findAll();
        }

        final GetAllCardsResponse response = new GetAllCardsResponse();
        List<Card> cards = results
                .stream()
                .map(CardConverter::convert)
                .toList();
        response.setCards(cards);

        return response;
    }
}
