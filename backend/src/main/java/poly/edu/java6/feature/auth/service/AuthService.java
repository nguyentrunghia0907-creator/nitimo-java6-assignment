package poly.edu.java6.feature.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import poly.edu.java6.feature.auth.dto.logup.LogupRequest;
import poly.edu.java6.feature.auth.repository.AuthRepository;
import poly.edu.java6.model.User;
import poly.edu.java6.utils.IdUtils;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean checkLogin(String input, String rawPassword) {
        String normalized = (input == null) ? "" : input.trim();
        String raw = (rawPassword == null) ? "" : rawPassword;

        Optional<User> userOpt = authRepository.findByUsernameOrEmailOrPhone(normalized, normalized, normalized);
        if (userOpt.isPresent()) {
            String stored = userOpt.get().getPassword();
            if (stored == null) return false;

            boolean looksLikeBcrypt = stored.startsWith("$2a$") || stored.startsWith("$2b$") || stored.startsWith("$2y$");
            if (looksLikeBcrypt) {
                return passwordEncoder.matches(raw, stored);
            }

            return stored.equals(raw);
        }
        return false;
    }

    public String signup(LogupRequest req) {
        String email = req.getEmail();
        if (authRepository.findByEmail(email).isPresent()) {
            return "Email exsited!";
        }

        if (!req.getPassword().equals(req.getConfirmPassword())) {
            return "Confirm password unsuccess!";
        }

        User user = new User();
        String newId = IdUtils.generateNewId();
        user.setUsername(newId);
        user.setFullName(req.getFullName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        authRepository.save(user);
        return "Đăng ký thành công!";
    }
}
