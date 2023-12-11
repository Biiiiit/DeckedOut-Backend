package strategy_card_game.Domain.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private long id;
    @NotBlank
    private String username;
    @NotNull
    private  String email;
    @NotNull
    private String password;
    private String newPassword;
    @JsonIgnore
    private MultipartFile avatar;
}
