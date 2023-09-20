package strategy_card_game.Domain;

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
public class UpdateCardRequest {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Type type;
    @NotNull
    private Integer damage;
    @NotNull
    private Integer healing;
    @NotNull
    private  Integer shielding;
}