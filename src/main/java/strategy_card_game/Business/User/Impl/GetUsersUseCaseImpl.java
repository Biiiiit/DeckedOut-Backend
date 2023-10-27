package strategy_card_game.Business.User.Impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import strategy_card_game.Business.User.GetUsersUseCase;
import strategy_card_game.Domain.User.GetAllUsersRequest;
import strategy_card_game.Domain.User.GetAllUsersResponse;
import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUsersUseCaseImpl implements GetUsersUseCase {
    private UserRepository userRepository;

    @Override
    @Transactional
    public GetAllUsersResponse getUsers(final GetAllUsersRequest request) {
        List<UserEntity> results = userRepository.findAll();

        final GetAllUsersResponse response = new GetAllUsersResponse();
        List<User> users = results
                .stream()
                .map(UserConverter::convert)
                .toList();
        response.setUsers(users);

        return response;
    }
}
