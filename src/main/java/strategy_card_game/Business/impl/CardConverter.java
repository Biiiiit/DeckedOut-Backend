package strategy_card_game.Business.impl;

import strategy_card_game.Domain.Card;
import strategy_card_game.Persistance.Entity.CardEntity;

final class CardConverter {
    private CardConverter() {
    }

    public static Card convert(CardEntity card) {
        return Card.builder()
                .id(card.getId())
                .name(card.getName())
                .type(card.getType())
                .damage(card.getDamage())
                .healing(card.getHealing())
                .shielding(card.getShielding())
                .build();
    }
}
