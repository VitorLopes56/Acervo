package br.com.biblioteca.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroDTO {
    private Long id;
    @NotBlank(message = "Título é obrigatório")
    private String titulo;
    private String autor;
    private String codigo;
    private String categoria;
    private Integer anoPublicacao;
    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;
}
