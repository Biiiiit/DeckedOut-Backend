package strategy_card_game.Business.Character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import strategy_card_game.Business.Playable_Character.Impl.DeleteCharacterUseCaseImpl;
import strategy_card_game.Business.Playable_Character.Impl.GetCharactersUseCaseImpl;
import strategy_card_game.Domain.Playable_Character.PlayableCharacter;
import strategy_card_game.Persistance.CharacterRepository;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteCharacterUseCaseImplTest {
    @InjectMocks
    private DeleteCharacterUseCaseImpl deleteCharacterUseCase;

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private GetCharactersUseCaseImpl getCharactersUseCase;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testDeleteCharacter() {
        // Create a mock character for testing
        PlayableCharacter character = new PlayableCharacter(1L, "CharacterName", "Description", 50, 0, new ArrayList<>(), new byte[0]);

        // Mock the behavior of characterRepository.deleteById to do nothing when the character is deleted
        doNothing().when(characterRepository).deleteById(character.getId());

        // Delete the character
        deleteCharacterUseCase.deleteCharacter(character.getId());

        // Verify that the characterRepository's deleteById method was called
        verify(characterRepository, times(1)).deleteById(character.getId());
    }
}
