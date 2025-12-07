package br.com.biblioteca.service;

import br.com.biblioteca.model.Emprestimo;
import br.com.biblioteca.model.Livro;
import br.com.biblioteca.repository.EmprestimoRepository;
import br.com.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepo;
    private final LivroRepository livroRepo;

    public EmprestimoService(EmprestimoRepository emprestimoRepo, LivroRepository livroRepo) {
        this.emprestimoRepo = emprestimoRepo;
        this.livroRepo = livroRepo;
    }

    @Transactional
    public Emprestimo solicitarEmprestimo(Long livroId, Long clienteId) {
        Livro livro = livroRepo.findById(livroId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado"));

        if (livro.getQuantidade() <= 0) {
            throw new IllegalStateException("Não há exemplares disponíveis para este livro.");
        }

        livro.setQuantidade(livro.getQuantidade() - 1);
        livroRepo.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivroId(livroId);
        emprestimo.setClienteId(clienteId);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setStatus("ATIVO");

        return emprestimoRepo.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolver(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepo.findById(emprestimoId)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));

        Livro livro = livroRepo.findById(emprestimo.getLivroId())
                .orElseThrow(() -> new IllegalStateException("Livro associado ao empréstimo não encontrado"));

        livro.setQuantidade(livro.getQuantidade() + 1);
        livroRepo.save(livro);

        emprestimo.setStatus("DEVOLVIDO");
        emprestimo.setDataDevolucao(LocalDate.now());
        return emprestimoRepo.save(emprestimo);
    }

    public List<Emprestimo> listar() {
        return emprestimoRepo.findAll();
    }

    public Optional<Emprestimo> porId(Long id) {
        return emprestimoRepo.findById(id);
    }

    public List<Emprestimo> findByClienteId(Long clienteId) {
        return emprestimoRepo.findByClienteId(clienteId);
    }
}
