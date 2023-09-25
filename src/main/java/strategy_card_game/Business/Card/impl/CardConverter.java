package strategy_card_game.Business.Card.impl;

import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Persistance.Entity.CardEntity;

final class CardConverter {
    private CardConverter() {
    }

    public static Card convert(CardEntity card) {
        return Card.builder()
                .id(card.getId())
                .name(card.getName())
                .typeOfCard(card.getTypeOfCard())
                .damage(card.getDamage())
                .healing(card.getHealing())
                .shielding(card.getShielding())
                .build();
    }
}
