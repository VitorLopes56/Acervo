package br.com.biblioteca.service;

import br.com.biblioteca.model.Estoque;
import br.com.biblioteca.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {
    private final EstoqueRepository repo;
    public EstoqueService(EstoqueRepository repo) { this.repo = repo; }

    public Estoque salvar(Estoque e) { return repo.save(e); }
    public Optional<Estoque> porLivroId(Long livroId) { return repo.findByLivroId(livroId); }
}
