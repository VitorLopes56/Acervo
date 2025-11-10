package br.com.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import jakarta.persistence.Column;


@Entity
@Table(name = "emprestimo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cliente_id")
    private Long clienteId;
    @Column(name = "livro_id")
    private Long livroId;
    @Column(name = "data_emprestimo")
    private LocalDate dataEmprestimo;
    @Column(name = "data_devolucao")
    private LocalDate dataDevolucao;
    @Column(name = "status")
    private String status;
}
