package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@Entity
@Table(name="games")
@NoArgsConstructor
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Lob
    @Column(name="icon", columnDefinition = "BLOB")
    private byte[] icon;
    @Lob
    @Column(name="banner", columnDefinition = "BLOB")
    private byte [] banner;
    @Column(name="areas")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="areaID")
    private List<AreaEntity> gameAreas;
    @Column(name="cards")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="cardID")
    private List<CardEntity> gameCards;
    @Column(name="enemies")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="enemyID")
    private List<EnemyEntity> gameEnemies;
    @Column(name="levels")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="levelID")
    private List<LevelEntity> gameLevels;
    @Column(name="characters")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="characterID")
    private List<CharacterEntity> gameCharacters;

    public GameEntity(long id, String name, String description, byte[] icon, byte[] banner, List<AreaEntity> gameAreas, List<CardEntity> gameCards, List<EnemyEntity> gameEnemies, List<LevelEntity> gameLevels, List<CharacterEntity> gameCharacters) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.banner = banner;
        this.gameAreas = gameAreas;
        this.gameCards = gameCards;
        this.gameEnemies = gameEnemies;
        this.gameLevels = gameLevels;
        this.gameCharacters = gameCharacters;
    }
}
