package strategy_card_game.Business.User.Impl;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import strategy_card_game.Business.User.Exception.InvalidUserException;
import strategy_card_game.Business.User.UpdateUserUseCase;
import strategy_card_game.Domain.User.UpdateUserRequest;
import strategy_card_game.Persistance.Entity.UserEntity;
import strategy_card_game.Persistance.UserRepository;
import strategy_card_game.Service.FirebaseFileStorageService;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private static final Logger logger = LoggerFactory.getLogger(UpdateUserUseCaseImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseFileStorageService firebaseFileStorageService;

    @Override
    public void updateUser(UpdateUserRequest userRequest) {
        try {
            logger.info("Received update request: {}", userRequest);

            Optional<UserEntity> userOptional = userRepository.findById(userRequest.getId());
            if (userOptional.isEmpty()) {
                throw new InvalidUserException("USER_ID_INVALID");
            }

            UserEntity user = userOptional.get();

            UpdateUserRequest request = userRequest;

            for (Field field : UpdateUserRequest.class.getDeclaredFields()) {
                if (field.getType() == String.class) {
                    field.setAccessible(true); // Make the private field accessible

                    try {
                        String value = (String) field.get(request);
                        if (value != null) {
                            // Remove extra double quotes
                            value = value.replaceAll("\"", "");
                            field.set(request, value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace(); // Handle or log the exception
                    }
                }
            }
            logger.info("Request password {}", request.getPassword());
            logger.info("User password: {}", user.getPassword());
            if (request.getPassword().trim().equals(user.getPassword().trim())) {
                updateFields(request, user);
            } else if (isPasswordValid(request.getPassword(), user.getPassword())) {
                // If valid, update the user fields including the password
                updateFields(request, user);
            } else {
                throw new InvalidUserException("INVALID_PASSWORD");
            }

            logger.info("User updated successfully: {}", user);
        } catch (Exception e) {
            logger.error("Error updating user:", e);
            throw e; // rethrow the exception after logging
        }
    }

    private void updateFields(UpdateUserRequest request, UserEntity user) {
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Check if newPassword is provided; if not, use the old password
        String newPassword = request.getNewPassword();
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword)); // Save the newPassword after encoding
        } else {
            user.setPassword(user.getPassword()); // Use the old password
        }

        // Update avatar if a new one is provided
        MultipartFile avatarFile = request.getAvatar();
        if (avatarFile != null && !avatarFile.isEmpty()) {
            try {
                byte[] avatarBytes = avatarFile.getBytes();
                // Save the avatar file and get the file location
                String fileLocation = saveAvatarFile(avatarBytes);
                user.setAvatar(fileLocation);
            } catch (IOException e) {
                throw new RuntimeException("Error processing avatar file", e);
            }
        }

        userRepository.save(user);
    }

    private String saveAvatarFile(byte[] avatarBytes) {
        try {
            // Use the Firebase file storage service to save the avatar file
            String fileLocation = firebaseFileStorageService.saveAvatarFile(avatarBytes);
            logger.info("Avatar file saved successfully at: {}", fileLocation);
            return fileLocation;
        } catch (Exception e) {
            logger.error("Error saving avatar file:", e);
            throw new RuntimeException("Error saving avatar file", e);
        }
    }
    private boolean isPasswordValid(String providedPassword, String storedPassword) {
        return passwordEncoder.matches(providedPassword.trim(), storedPassword.trim());
    }
}
