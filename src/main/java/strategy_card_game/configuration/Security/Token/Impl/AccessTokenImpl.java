package strategy_card_game.configuration.Security.Token.Impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import strategy_card_game.configuration.Security.Token.AccessToken;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@EqualsAndHashCode
@Getter
public class AccessTokenImpl implements AccessToken {
    private final String subject;
    private final Long Id;
    private final Set<String> roles;

    public AccessTokenImpl(String subject, Long Id, Collection<String> roles) {
        this.subject = subject;
        this.Id = Id;
        this.roles = roles != null ? Set.copyOf(roles) : Collections.emptySet();
    }

    @Override
    public boolean hasRole(String roleName) {
        return this.roles.contains(roleName);
    }
}
