package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name="enemies")
@NoArgsConstructor
public class EnemyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="health")
    private Integer health;
    @Column(name="attack")
    private Integer atk;
    @Column(name="healing")
    private Integer healing;
    @Column(name="shielding")
    private Integer shielding;
    @Column(name="pattern")
    private String pattern;
    @Lob
    @Column(name="sprite", columnDefinition = "BLOB")
    private byte[] sprite;

    public EnemyEntity(Long id, String name, Integer health, Integer atk, Integer healing, Integer shielding, String pattern, byte[] sprite) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.atk = atk;
        this.healing = healing;
        this.shielding = shielding;
        this.pattern = pattern;
        this.sprite = sprite;
    }
}
