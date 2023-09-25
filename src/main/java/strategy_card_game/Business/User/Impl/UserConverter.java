package strategy_card_game.Business.User.Impl;

import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.UserEntity;

public class UserConverter {
    private UserConverter() {
    }

    public static User convert(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .type(user.getType())
                .build();
    }
}
