package br.com.biblioteca.controller;

import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.ClienteService;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioViewController {

    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;
    private final ClienteService clienteService;

    public FuncionarioViewController(UsuarioService usuarioService, EmprestimoService emprestimoService, ClienteService clienteService) {
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public String painelFuncionario(Model model, Authentication authentication) {
        // Adiciona o usuário funcionário ao modelo
        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        model.addAttribute("funcionario", usuario);

        // Busca e ordena os empréstimos (usando o método correto 'listar')
        List<Emprestimo> emprestimos = emprestimoService.listar()
                .stream()
                .sorted(Comparator.comparing(Emprestimo::getDataEmprestimo).reversed())
                .collect(Collectors.toList());
        model.addAttribute("emprestimos", emprestimos);

        // Busca e ordena os clientes (usando o método correto 'listarTodos')
        List<Cliente> clientes = clienteService.listarTodos()
                .stream()
                .sorted(Comparator.comparing(Cliente::getNome))
                .collect(Collectors.toList());
        model.addAttribute("clientes", clientes);

        return "funcionario/funcionario";
    }
}
