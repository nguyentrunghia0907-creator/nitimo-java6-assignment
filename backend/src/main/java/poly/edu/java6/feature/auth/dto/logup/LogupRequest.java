package poly.edu.java6.feature.auth.dto.logup;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogupRequest {
    @NotBlank(message = "Username is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Confirm Password is required")
    private String confirmPassword;
}
