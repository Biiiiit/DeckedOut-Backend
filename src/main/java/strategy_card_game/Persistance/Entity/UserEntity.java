package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.User.TypeOfUser;

import java.util.List;

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
    @OneToMany(mappedBy = "developer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GameEntity> developedGames;


    public UserEntity(Long id, String username, String email, String password, TypeOfUser type, String avatar, List<GameEntity> developedGames) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.avatar = avatar;
        this.developedGames = developedGames;
    }
}

