package strategy_card_game.Domain.User;


public enum TypeOfUser {
    normal,
    admin;

    public static TypeOfUser fromString(String text) {
        for (TypeOfUser userTypeOfUser : TypeOfUser.values()) {
            if (userTypeOfUser.toString().equalsIgnoreCase(text)) {
                return userTypeOfUser;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + text + "]");
    }
}
