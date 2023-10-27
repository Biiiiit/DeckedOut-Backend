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
    @Column(name="ID")
    private Long id;
    @Column(name="Name")
    private String username;
    @Column(name="Email")
    private String email;
    @Column(name="Password")
    private String password;
    @Column(name="Type")
    private TypeOfUser type;

    public UserEntity(Long id, String username, String email, String password, TypeOfUser type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}
