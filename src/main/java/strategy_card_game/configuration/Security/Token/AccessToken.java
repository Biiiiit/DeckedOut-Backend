package strategy_card_game.configuration.Security.Token;

import java.util.Set;

public interface AccessToken {
    String getSubject();

    Long getId();

    Set<String> getRoles();

    boolean hasRole(String roleName);
}
