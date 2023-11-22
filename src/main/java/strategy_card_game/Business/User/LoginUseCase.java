package strategy_card_game.Business.User;

import strategy_card_game.Domain.User.LoginRequest;
import strategy_card_game.Domain.User.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
