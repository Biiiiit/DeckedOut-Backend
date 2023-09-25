package strategy_card_game.Persistance.Entity;

import lombok.Builder;
import lombok.Data;
import strategy_card_game.Domain.User.TypeOfUser;

@Builder
@Data
public class UserEntity {
    private Long id;
    private String username;
    private String email;
    private String password;
    private TypeOfUser type;

    public UserEntity(Long id, String username, String email, String password, TypeOfUser type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
