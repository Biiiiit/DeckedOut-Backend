package strategy_card_game.Domain.Enemy;

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
public class UpdateEnemyRequest {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private Integer health;
    @NotBlank
    private Integer atk;
    @NotBlank
    private Integer healing;
    @NotBlank
    private Integer shielding;
    @NotBlank
    private String pattern;
    @NotNull
    private byte[] sprite;
}
