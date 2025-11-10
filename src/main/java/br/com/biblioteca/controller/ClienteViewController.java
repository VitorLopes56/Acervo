package br.com.biblioteca.controller;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.LivroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/cliente")
public class ClienteViewController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;
    private final EmprestimoService emprestimoService;

    public ClienteViewController(LivroService livroService, LivroMapper livroMapper, EmprestimoService emprestimoService) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/livros")
    public String listarLivros(@RequestParam(value = "q", required = false) String q,
                               @RequestParam(value = "filtro", required = false) String filtro,
                               Model model) {
        List<LivroDTO> livros;
        if (q == null || q.isBlank()) {
            livros = livroService.listar().stream().map(livroMapper::toDto).collect(Collectors.toList());
        } else {
            if ("autor".equalsIgnoreCase(filtro)) {
                livros = livroService.procurarPorAutor(q).stream().map(livroMapper::toDto).collect(Collectors.toList());
            } else if ("categoria".equalsIgnoreCase(filtro)) {
                livros = livroService.procurarPorCategoria(q).stream().map(livroMapper::toDto).collect(Collectors.toList());
            } else {
                livros = livroService.procurarPorTitulo(q).stream().map(livroMapper::toDto).collect(Collectors.toList());
            }
        }
        model.addAttribute("livros", livros);
        return "cliente/livros";
    }

    @GetMapping("/emprestar/{livroId}")
    public String emprestarForm(@PathVariable Long livroId, Model model) {
        EmprestimoDTO e = new EmprestimoDTO();
        e.setLivroId(livroId);
        model.addAttribute("emprestimo", e);
        return "cliente/emprestar";
    }


    @PostMapping("/emprestar")
    public String processarEmprestimo(@ModelAttribute Emprestimo emprestimo) {
        // Método gerado automaticamente — ajuste a lógica aqui conforme a implementação de EmprestimoService/Mapper
        try {
            // se necessário, associe um cliente padrão ou implemente lógica de busca do cliente autenticado
        } catch (Exception e) {
        }
        return "redirect:/cliente/livros";
    }

}
