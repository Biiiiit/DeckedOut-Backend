package strategy_card_game.Business.User;

import org.junit.jupiter.api.Test;
import strategy_card_game.Domain.User.TypeOfUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TypeOfUserTest {

    @Test
    public void testFromString() {
        // Test with a valid string
        String validString = "normal";
        TypeOfUser normalType = TypeOfUser.fromString(validString);
        assertEquals(TypeOfUser.normal, normalType);

        // Test with a different valid string
        validString = "admin";
        TypeOfUser adminType = TypeOfUser.fromString(validString);
        assertEquals(TypeOfUser.admin, adminType);

        // Test with an invalid string
        String invalidString = "invalidType";
        assertThrows(IllegalArgumentException.class, () -> {
            TypeOfUser.fromString(invalidString);
        });
    }
}
