package strategy_card_game.Domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Game.Game;

import java.util.List;

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
    private String avatar;

    public User(String username, String email, String password, TypeOfUser type, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.avatar = avatar;
    }
}

