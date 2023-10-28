package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@Entity
@Table(name="levels")
@NoArgsConstructor
public class LevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="levelID")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="enemies")
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="enemyID")
    private List<EnemyEntity> enemies;
    @Lob
    @Column(name="lvlSprite", columnDefinition = "BLOB")
    private byte[] lvlSprite;
    @Lob
    @Column(name="backgroundSprite", columnDefinition = "BLOB")
    private byte[] backgroundSprite;

    public LevelEntity(Long id, String name, List<EnemyEntity> enemies, byte[] lvlSprite, byte[] backgroundSprite) {
        this.id = id;
        this.name = name;
        this.enemies = enemies;
        this.lvlSprite = lvlSprite;
        this.backgroundSprite = backgroundSprite;
    }
}
