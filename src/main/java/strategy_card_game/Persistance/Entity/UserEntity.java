package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.User.TypeOfUser;

@Builder
@Data
@Entity
@Table(name="users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="type")
    private TypeOfUser type;
    @Column(name="avatar")
    private String avatar;

    public UserEntity(Long id, String username, String email, String password, TypeOfUser type, String avatar) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.avatar = avatar;
    }
}

