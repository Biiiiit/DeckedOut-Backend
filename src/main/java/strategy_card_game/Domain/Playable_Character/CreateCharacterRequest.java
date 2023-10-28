package strategy_card_game.Domain.Playable_Character;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Card.Card;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCharacterRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private Integer health;
    @NotBlank
    private Integer ammo;
    @NotNull
    private List<Card> startingDeck;
    @NotNull
    private byte[] sprite;
}
