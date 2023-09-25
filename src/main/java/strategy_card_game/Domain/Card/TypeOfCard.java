package strategy_card_game.Domain.Card;

public enum TypeOfCard {
    Atk,
    Heal,
    Shield,
    Effect;

    public static TypeOfCard fromString(String text) {
        for (TypeOfCard cardTypeOfCard : TypeOfCard.values()) {
            if (cardTypeOfCard.toString().equalsIgnoreCase(text)) {
                return cardTypeOfCard;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + text + "]");
    }
}
