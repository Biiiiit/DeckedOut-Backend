package strategy_card_game.configuration.Security.Token;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
