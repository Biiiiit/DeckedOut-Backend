package strategy_card_game.Domain.Card;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCardsRequest {
    @NotBlank
    private String name;
    @NotNull
    private TypeOfCard typeOfCard;
    @NotNull
    private Integer damage;
    @NotNull
    private Integer healing;
    @NotNull
    private Integer shielding;
}
