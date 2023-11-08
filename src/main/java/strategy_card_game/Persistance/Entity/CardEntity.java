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
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="type")
    private TypeOfCard typeOfCard;
    @Column(name="damage")
    private Integer damage;
    @Column(name="healing")
    private Integer healing;
    @Column(name="shielding")
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



