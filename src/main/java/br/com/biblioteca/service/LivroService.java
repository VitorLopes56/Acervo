package br.com.biblioteca.service;

import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository repo;
    public LivroService(LivroRepository repo) { this.repo = repo; }

    public Livro salvar(Livro l) { return repo.save(l); }
    public List<Livro> listar() { return repo.findAll(); }
    public Optional<Livro> porId(Long id) { return repo.findById(id); }
    public void excluir(Long id) { repo.deleteById(id); }

    public List<Livro> procurarPorTitulo(String titulo) { return repo.findByTituloContainingIgnoreCase(titulo); }
    public List<Livro> procurarPorAutor(String autor) { return repo.findByAutorContainingIgnoreCase(autor); }
    public List<Livro> procurarPorCategoria(String categoria) { return repo.findByCategoriaContainingIgnoreCase(categoria); }
}
