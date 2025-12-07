package br.com.biblioteca.controller;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cliente")
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
    @Operation(summary = "Lista ou busca livros disponíveis")
    public ResponseEntity<List<LivroDTO>> listarOuBuscarLivros(@RequestParam(value = "q", required = false) String query) {
        List<LivroDTO> livros;
        if (query == null || query.isBlank()) {
            livros = livroService.listar().stream()
                    .map(livroMapper::toDto)
                    .collect(Collectors.toList());
        } else {
            livros = livroService.searchByTituloOrAutor(query).stream()
                    .map(livroMapper::toDto)
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/emprestimos")
    @Operation(summary = "Solicita o empréstimo de um livro")
    @ApiResponse(responseCode = "200", description = "Empréstimo solicitado com sucesso")
    public ResponseEntity<Void> solicitarEmprestimo(@RequestBody EmprestimoRequest emprestimoRequest) {
        // O ideal é pegar o ID do cliente a partir do usuário autenticado
        long clienteId = 1L; // Mockado por enquanto
        emprestimoService.solicitarEmprestimo(emprestimoRequest.getLivroId(), clienteId);
        return ResponseEntity.ok().build();
    }

    // Classe auxiliar
    public static class EmprestimoRequest {
        private Long livroId;
        public Long getLivroId() { return livroId; }
        public void setLivroId(Long livroId) { this.livroId = livroId; }
    }
}
