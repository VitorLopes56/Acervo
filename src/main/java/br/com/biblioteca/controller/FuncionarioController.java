package br.com.biblioteca.controller;

import br.com.biblioteca.dto.ClienteDTO;
import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.mapper.ClienteMapper;
import br.com.biblioteca.mapper.LivroMapper;
import br.com.biblioteca.model.Cliente;
import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.service.ClienteService;
import br.com.biblioteca.service.EmprestimoService;
import br.com.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

    private final LivroService livroService;
    private final ClienteService clienteService;
    private final EmprestimoService emprestimoService;
    private final LivroMapper livroMapper;
    private final ClienteMapper clienteMapper;

    public FuncionarioController(LivroService livroService, ClienteService clienteService, EmprestimoService emprestimoService, LivroMapper livroMapper, ClienteMapper clienteMapper) {
        this.livroService = livroService;
        this.clienteService = clienteService;
        this.emprestimoService = emprestimoService;
        this.livroMapper = livroMapper;
        this.clienteMapper = clienteMapper;
    }

    // --- Endpoints de Livros ---
    @GetMapping("/livros")
    public ResponseEntity<List<LivroDTO>> buscarLivros(@RequestParam(value = "q", required = false) String query) {
        List<Livro> livros = (query == null || query.isBlank()) ? livroService.listar() : livroService.searchByTituloOrAutor(query);
        return ResponseEntity.ok(livros.stream().map(livroMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/livros")
    public ResponseEntity<LivroDTO> salvarLivro(@Valid @RequestBody LivroDTO livroDTO) {
        Livro novoLivro = livroService.salvar(livroMapper.toEntity(livroDTO));
        return ResponseEntity.ok(livroMapper.toDto(novoLivro));
    }

    // --- Endpoints de Clientes ---
    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteDTO>> buscarClientes(@RequestParam(value = "q", required = false) String query) {
        List<Cliente> clientes = (query == null || query.isBlank()) ? clienteService.listarTodos() : clienteService.searchByNomeOrCpf(query);
        return ResponseEntity.ok(clientes.stream().map(clienteMapper::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/clientes")
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente novoCliente = clienteService.salvar(clienteMapper.toEntity(clienteDTO));
        return ResponseEntity.ok(clienteMapper.toDto(novoCliente));
    }

    // --- Endpoints de Empréstimos ---
    @GetMapping("/emprestimos")
    public ResponseEntity<List<Emprestimo>> buscarEmprestimos() {
        // Usando o método correto 'listar'
        return ResponseEntity.ok(emprestimoService.listar());
    }

    @PostMapping("/emprestimos/{id}/devolver")
    public ResponseEntity<Void> devolverLivro(@PathVariable Long id) {
        emprestimoService.devolver(id);
        return ResponseEntity.ok().build();
    }
}
