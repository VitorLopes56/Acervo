package br.com.biblioteca.repository;
import br.com.biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    // Método de busca flexível por título ou autor
    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(l.autor) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Livro> searchByTituloOrAutor(@Param("query") String query);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    List<Livro> findByAutorContainingIgnoreCase(String autor);
    List<Livro> findByCategoriaContainingIgnoreCase(String categoria);
}
