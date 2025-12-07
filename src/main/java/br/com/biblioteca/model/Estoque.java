package br.com.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Column;


@Entity
@Table(name = "estoque")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "livro_id")
    private Long livroId;
    @Column(name = "quantidade_total")

    private Integer quantidadeTotal;
    @Column(name = "quantidade_emprestada")
    private Integer quantidadeEmprestada;
}
