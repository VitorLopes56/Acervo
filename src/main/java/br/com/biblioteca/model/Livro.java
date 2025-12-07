package br.com.biblioteca.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Column;


@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "autor")
    private String autor;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "categoria")
    private String categoria;
    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;
    @Column(name = "quantidade")
    private Integer quantidade;
}
