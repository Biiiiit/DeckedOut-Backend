package strategy_card_game.Business.User;

import strategy_card_game.Domain.User.User;

import java.util.Optional;

public interface GetUserUseCase {
    Optional<User> getUser(long userID);
}
