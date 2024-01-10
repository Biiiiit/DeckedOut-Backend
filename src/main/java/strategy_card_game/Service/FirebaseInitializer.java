package strategy_card_game.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;
public class FirebaseInitializer {

    public static void initializeFirebase() {
        try {
            FileInputStream serviceAccount = new FileInputStream("C:/Users/ruben/Downloads/strategy-card-game/src/main/resources/raw/decked-out-407512-firebase-adminsdk-yj98i-8d05301ea5.json");

            if (serviceAccount == null) {
                throw new RuntimeException("Service account JSON file not found.");
            }

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setStorageBucket("decked-out-407512.appspot.com")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error initializing Firebase", e);
        }
    }
}
