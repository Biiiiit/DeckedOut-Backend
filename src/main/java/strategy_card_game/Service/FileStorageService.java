package strategy_card_game.Service;

public interface FileStorageService {
    String saveAvatarFile(byte[] avatarBytes);
    byte[] getAvatarFile(String fileName);
}
