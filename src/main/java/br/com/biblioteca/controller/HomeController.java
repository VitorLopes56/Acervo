package br.com.biblioteca.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // Verifica se o usuário tem a role de FUNCIONARIO
            boolean isFuncionario = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch("ROLE_FUNCIONARIO"::equals);

            if (isFuncionario) {
                return "redirect:/funcionario/livros";
            } else {
                return "redirect:/cliente/livros";
            }
        }
        // Se não estiver autenticado, redireciona para a página de login
        return "redirect:/login";
    }
}
