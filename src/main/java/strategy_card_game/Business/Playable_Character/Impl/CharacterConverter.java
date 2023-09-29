package strategy_card_game.Business.Playable_Character.Impl;

import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.Entity.CharacterEntity;

public class CharacterConverter {

    private CharacterConverter(){
    }

    public static PlayableCharacter convert(CharacterEntity character) {
        return PlayableCharacter.builder()
                .id(character.getId())
                .name(character.getName())
                .description(character.getDescription())
                .health(character.getHealth())
                .ammo(character.getAmmo())
                .startingDeck(character.getStartingDeck())
                .sprite(character.getSprite())
                .build();
    }
}
