package br.com.biblioteca.controller;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Usuario;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.LivroService;
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
@RequestMapping("/cliente")
public class ClienteViewController {

    private final LivroService livroService;
    private final UsuarioService usuarioService;
    private final EmprestimoService emprestimoService;
    private final LivroMapper livroMapper;

    public ClienteViewController(LivroService livroService, UsuarioService usuarioService, EmprestimoService emprestimoService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.usuarioService = usuarioService;
        this.emprestimoService = emprestimoService;
        this.livroMapper = livroMapper;
    }

    @GetMapping
    public String painelCliente(Model model, Authentication authentication) {
        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        model.addAttribute("cliente", usuario);

        // Busca e ordena os empréstimos (mais recentes primeiro)
        List<Emprestimo> emprestimos = emprestimoService.findByClienteId(usuario.getId())
                .stream()
                .sorted(Comparator.comparing(Emprestimo::getDataEmprestimo).reversed())
                .collect(Collectors.toList());
        model.addAttribute("emprestimos", emprestimos);

        // Busca e ordena os livros (ordem alfabética de título)
        List<LivroDTO> livros = livroService.listar().stream()
                .map(livroMapper::toDto)
                .sorted(Comparator.comparing(LivroDTO::getTitulo))
                .collect(Collectors.toList());
        model.addAttribute("livros", livros);

        return "cliente/cliente";
    }
}
