package strategy_card_game.Domain.Area;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import strategy_card_game.Domain.Level.Level;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    private Long id;
    private String name;
    private String description;
    private List<Level> listOfLevels;
    private byte[] backgroundSprite;
}