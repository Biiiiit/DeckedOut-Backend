package strategy_card_game.Business.User;

import strategy_card_game.Domain.User.UpdateUserRequest;

public interface UpdateUserUseCase {
    void updateUser(UpdateUserRequest request);
}
