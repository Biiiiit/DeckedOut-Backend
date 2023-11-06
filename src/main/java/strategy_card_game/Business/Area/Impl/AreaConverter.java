package strategy_card_game.Business.Area.Impl;

import strategy_card_game.Business.Level.Impl.LevelConverter;
import strategy_card_game.Domain.Area.Area;
import strategy_card_game.Domain.Level.Level;
import strategy_card_game.Persistance.Entity.AreaEntity;
import strategy_card_game.Persistance.Entity.LevelEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AreaConverter {
    public AreaConverter() {
    }

    public static Area convertToArea(AreaEntity area) {
        List<Level> levels = area.getListOfLevels()
                .stream()
                .map(LevelConverter::convertToLevel)
                .collect(Collectors.toList());

        return Area.builder()
                .id(area.getId())
                .name(area.getName())
                .description(area.getDescription())
                .listOfLevels(levels)
                .backgroundSprite(area.getBackgroundSprite())
                .build();
    }
    public static AreaEntity convertToAreaEntity(Area area) {
        List<LevelEntity> levels = area.getListOfLevels()
                .stream()
                .map(LevelConverter::convertToLevelEntity)
                .collect(Collectors.toList());

        return AreaEntity.builder()
                .id(area.getId())
                .name(area.getName())
                .description(area.getDescription())
                .listOfLevels(levels)
                .backgroundSprite(area.getBackgroundSprite())
                .build();
    }
}
