package br.com.biblioteca.repository;
import br.com.biblioteca.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByClienteId(Long clienteId);
    List<Emprestimo> findByLivroId(Long livroId);
}
