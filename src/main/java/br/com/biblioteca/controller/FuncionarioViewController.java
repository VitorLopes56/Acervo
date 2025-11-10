package br.com.biblioteca.controller;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioViewController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    public FuncionarioViewController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @GetMapping("/livros")
    public String listarLivros(Model model) {
        List<LivroDTO> livros = livroService.listar().stream()
                .map(livroMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("livros", livros);
        return "funcionario/livros";
    }

    @GetMapping("/livros/novo")
    public String novoLivroForm(Model model) {
        model.addAttribute("livro", new LivroDTO());
        return "funcionario/novo-livro";
    }

    @PostMapping("/livros")
    public String salvarLivro(@Valid @ModelAttribute("livro") LivroDTO livro, BindingResult br) {
        if (br.hasErrors()) {
            return "funcionario/novo-livro";
        }
        Livro entidade = livroMapper.toEntity(livro);
        livroService.salvar(entidade);
        return "redirect:/funcionario/livros";
    }
}
