package strategy_card_game.Business.User;

import strategy_card_game.Domain.User.GetAllUsersRequest;
import strategy_card_game.Domain.User.GetAllUsersResponse;

public interface GetUsersUseCase {
    GetAllUsersResponse getUsers(GetAllUsersRequest request);
}
