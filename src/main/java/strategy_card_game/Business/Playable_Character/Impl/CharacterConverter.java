package strategy_card_game.Business.Playable_Character.Impl;

import strategy_card_game.Business.Card.impl.CardConverter;
import strategy_card_game.Domain.Card.Card;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.List;
import java.util.stream.Collectors;

public class CharacterConverter {

    private CharacterConverter() {
    }

    public static PlayableCharacter convert(CharacterEntity character) {
        List<Card> startingDeck = character.getStartingDeck()
                .stream()
                .map(CardConverter::convertToCard)
                .collect(Collectors.toList());

        return PlayableCharacter.builder()
                .id(character.getId())
                .name(character.getName())
                .description(character.getDescription())
                .health(character.getHealth())
                .ammo(character.getAmmo())
                .startingDeck(startingDeck)
                .sprite(character.getSprite())
                .build();
    }
}
