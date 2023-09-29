package strategy_card_game.Persistance.Impl;

import org.springframework.stereotype.Repository;
import strategy_card_game.Persistance.CharacterRepository;
import strategy_card_game.Persistance.Entity.CharacterEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class FakeCharacterRepositoryImpl implements CharacterRepository {
    private static long NEXT_ID = 1;
    private final List<CharacterEntity>savedCharacters;
    public FakeCharacterRepositoryImpl() {this.savedCharacters = new ArrayList<>();}
    @Override
    public boolean existsByName(String name) {
        return this.savedCharacters
                .stream()
                .anyMatch(characterEntity -> characterEntity.getName().equals(name));
    }

    @Override
    public CharacterEntity save(CharacterEntity character) {
        if (character.getId() == null) {
            character.setId(NEXT_ID);
            NEXT_ID++;
            this.savedCharacters.add(character);
        }
        return character;
    }

    @Override
    public void deleteById(long characterId) {
        this.savedCharacters.removeIf(characterEntity -> characterEntity.getId().equals(characterId));
    }

    @Override
    public List<CharacterEntity> findAll() {
        return Collections.unmodifiableList(this.savedCharacters);
    }

    @Override
    public Optional<CharacterEntity> findById(long characterId) {
        return this.savedCharacters.stream()
                .filter(characterEntity -> characterEntity.getId().equals(characterId))
                .findFirst();
    }
}
