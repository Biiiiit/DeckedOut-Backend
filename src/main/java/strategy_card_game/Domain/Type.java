package strategy_card_game.Domain;

public enum Type {
    Atk_Normal,
    Heal,
    Shield,
    Effect;

    public static Type fromString(String text) {
        for (Type cardType : Type.values()) {
            if (cardType.toString().equalsIgnoreCase(text)) {
                return cardType;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + text + "]");
    }
}
