package br.com.biblioteca.dto;

import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmprestimoDTO {
    private Long id;
    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;
    @NotNull(message = "Livro é obrigatório")
    private Long livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status;
}
