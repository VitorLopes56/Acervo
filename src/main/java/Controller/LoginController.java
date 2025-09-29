package Controller; // seu pacote

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin() {
        return "login"; // vai buscar login.html em templates
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {

        // Usuário cliente de demonstração
        if ("cliente".equals(username) && "12345".equals(password)) {
            return "cliente"; // vai buscar cliente.html em templates
        }

        // Caso inválido
        model.addAttribute("error", "Usuário ou senha inválidos!");
        return "login";
    }
}
