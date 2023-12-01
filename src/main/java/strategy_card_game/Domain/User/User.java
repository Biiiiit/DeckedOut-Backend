package strategy_card_game.Domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private TypeOfUser type;
    private byte[] avatar;

    public User(String username, String email, String password, TypeOfUser type, byte[] avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.avatar = avatar;
    }
}

