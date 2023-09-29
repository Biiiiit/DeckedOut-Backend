package strategy_card_game.Domain.Playable_Character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCharactersResponse {
    private List<PlayableCharacter> characters;
}
