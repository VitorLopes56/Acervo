package br.com.biblioteca.controller;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Livro;
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
@RequestMapping("/api/funcionario")
@Tag(name = "Funcionário", description = "Endpoints para gerenciamento da biblioteca")
public class FuncionarioController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    public FuncionarioController(LivroService livroService, LivroMapper livroMapper) {
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @GetMapping("/livros")
    @Operation(summary = "Lista todos os livros cadastrados")
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        List<LivroDTO> livros = livroService.listar().stream()
                .map(livroMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/livros")
    @Operation(summary = "Cadastra um novo livro")
    @ApiResponse(responseCode = "200", description = "Livro cadastrado com sucesso")
    public ResponseEntity<LivroDTO> salvarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        Livro entidade = livroMapper.toEntity(livroDTO);
        Livro novoLivro = livroService.salvar(entidade);
        return ResponseEntity.ok(livroMapper.toDto(novoLivro));
    }
}
