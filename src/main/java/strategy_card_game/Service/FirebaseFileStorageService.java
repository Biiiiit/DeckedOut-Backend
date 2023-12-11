package strategy_card_game.Service;

import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FirebaseFileStorageService implements FileStorageService {

    private final String bucketName;

    public FirebaseFileStorageService() {
        this.bucketName = "decked-out-407512.appspot.com";
    }

    @Override
    public String saveAvatarFile(byte[] avatarBytes) {
        String fileName = generateRandomFileName();
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(bucketName, "avatars/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, avatarBytes);
        return blob.getBlobId().getName();
    }

    @Override
    public byte[] getAvatarFile(String fileName) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Blob blob = storage.get(BlobId.of(bucketName, "avatars/" + fileName));
        return blob.getContent();
    }

    private String generateRandomFileName() {
        return "avatar_" + UUID.randomUUID().toString() + ".jpg";
    }
}
