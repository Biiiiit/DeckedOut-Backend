package strategy_card_game.Business.User;

import strategy_card_game.Domain.User.CreateUserRequest;
import strategy_card_game.Domain.User.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
