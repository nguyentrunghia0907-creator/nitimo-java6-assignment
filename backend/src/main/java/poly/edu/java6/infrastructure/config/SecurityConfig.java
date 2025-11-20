package poly.edu.java6.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Bỏ cấu hình mặc định CSRF và CORS
        http.csrf(config -> config.disable()).cors(config -> config.disable());
        // Phân quyền sử dụng
        http.authorizeHttpRequests(config -> {
            config
                    .requestMatchers("/poly/**").authenticated()
                    .anyRequest().permitAll();
        });
        // Form đăng nhập
        http.formLogin(config -> {
            config.loginPage("/auth");
            config.loginProcessingUrl("/login/check");
            config.defaultSuccessUrl("/login/success");
            config.failureUrl("/login/failure");
            config.permitAll();
            config.usernameParameter("username");
            config.passwordParameter("password");
        });
        // Ghi nhớ tài khoản
        http.rememberMe(config -> {
            config.tokenValiditySeconds(3*24*60*60);
            config.rememberMeCookieName("remember-me");
            config.rememberMeParameter("remember-me");
        });
        // Đăng xuất
        http.logout(config -> {
            config.logoutUrl("/logout");
            config.logoutSuccessUrl("/login/exit");
            config.clearAuthentication(true);
            config.invalidateHttpSession(true);
            config.deleteCookies("remember-me");
        });
        // Đăng xuất
        http.logout(Customizer.withDefaults());
        return http.build();
    }
}
