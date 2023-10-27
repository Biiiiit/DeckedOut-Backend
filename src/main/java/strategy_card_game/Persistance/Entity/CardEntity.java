package strategy_card_game.Persistance.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Card.TypeOfCard;

@Builder
@Data
@Entity
@Table(name="cards")
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;
    @Column(name="Name")
    private String name;
    @Column(name="Type")
    private TypeOfCard typeOfCard;
    @Column(name="Damage")
    private Integer damage;
    @Column(name="Healing")
    private Integer healing;
    @Column(name="Shielding")
    private Integer shielding;

    public CardEntity(Long id, String name, TypeOfCard typeOfCard, Integer damage, Integer healing, Integer shielding) {
        this.id = id;
        this.name = name;
        this.typeOfCard = typeOfCard;
        this.damage = damage;
        this.healing = healing;
        this.shielding = shielding;
    }
}



