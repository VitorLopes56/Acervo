package br.com.biblioteca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // A lógica de redirecionamento pós-login agora é centralizada no CustomAuthenticationSuccessHandler.
        // O HomeController apenas redireciona para a página de login se o usuário acessar a raiz.
        return "redirect:/login";
    }
}
