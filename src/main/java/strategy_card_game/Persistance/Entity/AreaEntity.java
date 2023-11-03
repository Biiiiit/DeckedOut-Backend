package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@Entity
@Table(name="areas")
@NoArgsConstructor
public class AreaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToMany
    @JoinTable(
            name = "list_of_levels",
            joinColumns = @JoinColumn(name = "area_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id")
    )
    private List<LevelEntity> listOfLevels;
    @Lob
    @Column(name = "backgroundSprite", columnDefinition = "BLOB")
    private byte[] backgroundSprite;

    public AreaEntity(Long id, String name, String description, List<LevelEntity> listOfLevels, byte[] backgroundSprite) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.listOfLevels = listOfLevels;
        this.backgroundSprite = backgroundSprite;
    }
}
