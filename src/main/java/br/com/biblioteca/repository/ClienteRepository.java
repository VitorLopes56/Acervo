package br.com.biblioteca.repository;

import br.com.biblioteca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :query, '%')) OR c.cpf LIKE CONCAT('%', :query, '%')")
    List<Cliente> searchByNomeOrCpf(@Param("query") String query);
}
