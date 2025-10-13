package com.biblioteca.sistema_biblioteca.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // Redireciona para a página de login
        return "login"; // usa templates/login.html
    }
}
