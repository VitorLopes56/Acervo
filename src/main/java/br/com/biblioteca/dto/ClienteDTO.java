package br.com.biblioteca.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    private String cpf;
    private String matricula;
    private String endereco;
    private String telefone;
    private String email;
}
