package strategy_card_game.Business.User.Impl;

import strategy_card_game.Domain.User.User;
import strategy_card_game.Persistance.Entity.UserEntity;

public class UserConverter {
    private UserConverter() {
    }

    public static User convertToUser(UserEntity user) {
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .type(user.getType())
                .build();
    }
    public static UserEntity convertToUserEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .type(user.getType())
                .build();
    }
}
