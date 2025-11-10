package br.com.biblioteca.mapper;

import br.com.biblioteca.dto.LivroDTO;
import br.com.biblioteca.model.Livro;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LivroMapper {
    LivroDTO toDto(Livro l);
    Livro toEntity(LivroDTO dto);
}
