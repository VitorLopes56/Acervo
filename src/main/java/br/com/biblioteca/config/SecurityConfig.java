package br.com.biblioteca.config;

import br.com.biblioteca.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UsuarioService usuarioService;

    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider ap = new DaoAuthenticationProvider();
        ap.setUserDetailsService(usuarioService);
        ap.setPasswordEncoder(passwordEncoder());
        return ap;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                        "/css/**", "/js/**", "/webjars/**", "/h2-console/**", "/login", "/error",
                        "/swagger-ui/**", "/v3/api-docs/**"
                    ).permitAll()
                    .requestMatchers("/funcionario/**").hasRole("FUNCIONARIO")
                    .requestMatchers("/cliente/**").hasAnyRole("CLIENTE","FUNCIONARIO")
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll())
            .csrf(AbstractHttpConfigurer::disable) // Desabilita o CSRF
            .headers(headers -> headers.frameOptions(headersC -> headersC.sameOrigin()))
            ;
        return http.build();
    }
}
