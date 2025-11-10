package br.com.biblioteca.controller;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Cliente", description = "Endpoints para clientes da biblioteca")
public class ClienteController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;
    private final EmprestimoService emprestimoService;

    public ClienteController(LivroService livroService, LivroMapper livroMapper, EmprestimoService emprestimoService) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/livros")
    @Operation(summary = "Lista todos os livros disponíveis")
    public ResponseEntity<List<LivroDTO>> listarLivros(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "filtro", required = false) String filtro) {
        
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
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/emprestar")
    @Operation(summary = "Solicita o empréstimo de um livro")
    @ApiResponse(responseCode = "200", description = "Empréstimo solicitado com sucesso")
    public ResponseEntity<Void> solicitarEmprestimo(@Valid @RequestBody EmprestimoDTO emprestimoDTO) {
        emprestimoService.solicitarEmprestimo(emprestimoDTO.getLivroId(), emprestimoDTO.getClienteId());
        return ResponseEntity.ok().build();
    }
}
