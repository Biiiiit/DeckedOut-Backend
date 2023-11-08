package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@Entity
@Table(name="characters")
@NoArgsConstructor
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "health")
    private Integer health;
    @Column(name = "ammo")
    private Integer ammo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "character_starting_deck",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    private List<CardEntity> startingDeck;
    @Lob
    @Column(name = "sprite", columnDefinition = "BLOB")
    private byte[] sprite;

    public CharacterEntity(Long id, String name, String description, Integer health, Integer ammo, List<CardEntity> startingDeck, byte[] sprite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.health = health;
        this.ammo = ammo;
        this.startingDeck = startingDeck;
        this.sprite = sprite;
    }
}
