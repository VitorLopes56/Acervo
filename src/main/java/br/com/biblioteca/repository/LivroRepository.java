package br.com.biblioteca.repository;
import br.com.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    List<Livro> findByCategoriaContainingIgnoreCase(String categoria);
}
