package br.com.biblioteca.repository;
import br.com.biblioteca.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Optional<Estoque> findByLivroId(Long livroId);
}
