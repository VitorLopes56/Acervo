package com.biblioteca.sistema_biblioteca.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login"; // procura login.html em templates
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {

        if ("cliente".equals(username) && "12345".equals(password)) {
            return "cliente"; // cliente.html em templates
        }

        model.addAttribute("error", "Usuário ou senha inválidos!");
        return "login";
    }
}

