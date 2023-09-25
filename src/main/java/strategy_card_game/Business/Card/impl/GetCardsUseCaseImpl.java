package strategy_card_game.Business.Card.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.Card.GetCardsUseCase;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Card.GetAllCardsRequest;
import strategy_card_game.Domain.Card.GetAllCardsResponse;
import strategy_card_game.Persistance.CardRepository;
import strategy_card_game.Persistance.Entity.CardEntity;

import java.util.List;

@Service
@AllArgsConstructor
public class GetCardsUseCaseImpl implements GetCardsUseCase {
    private CardRepository cardRepository;

    @Override
    public GetAllCardsResponse getCards(final GetAllCardsRequest request) {
        List<CardEntity> results = cardRepository.findAll();

        final GetAllCardsResponse response = new GetAllCardsResponse();
        List<Card> cards = results
                .stream()
                .map(CardConverter::convert)
                .toList();
        response.setCards(cards);

        return response;
    }
}
