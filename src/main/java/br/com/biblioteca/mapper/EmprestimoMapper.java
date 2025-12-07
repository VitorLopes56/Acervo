package br.com.biblioteca.mapper;

import br.com.biblioteca.dto.EmprestimoDTO;
import br.com.biblioteca.model.Emprestimo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {
    EmprestimoDTO toDto(Emprestimo e);
    Emprestimo toEntity(EmprestimoDTO dto);
}
