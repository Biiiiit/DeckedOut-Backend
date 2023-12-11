package strategy_card_game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import strategy_card_game.Service.FirebaseInitializer;

@SpringBootApplication
public class StrategyCardGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(StrategyCardGameApplication.class, args);

        FirebaseInitializer.initializeFirebase();
    }

}
