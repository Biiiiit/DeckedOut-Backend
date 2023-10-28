package strategy_card_game.Business.Card.impl;

import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Persistance.Entity.CardEntity;

public final class CardConverter {
    private CardConverter() {
    }

    public static Card convertToCard(CardEntity card) {
        return Card.builder()
                .id(card.getId())
                .name(card.getName())
                .typeOfCard(card.getTypeOfCard())
                .damage(card.getDamage())
                .healing(card.getHealing())
                .shielding(card.getShielding())
                .build();
    }

    public static CardEntity convertToCardEntity(Card card) {
        return CardEntity.builder()
                .id(card.getId())
                .name(card.getName())
                .typeOfCard(card.getTypeOfCard())
                .damage(card.getDamage())
                .healing(card.getHealing())
                .shielding(card.getShielding())
                .build();
    }
}
