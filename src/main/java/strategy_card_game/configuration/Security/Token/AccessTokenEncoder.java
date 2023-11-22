package strategy_card_game.configuration.Security.Token;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
