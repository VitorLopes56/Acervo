package br.com.biblioteca.mapper;

import br.com.biblioteca.dto.ClienteDTO;
import br.com.biblioteca.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    ClienteDTO toDto(Cliente c);
    Cliente toEntity(ClienteDTO dto);
}
