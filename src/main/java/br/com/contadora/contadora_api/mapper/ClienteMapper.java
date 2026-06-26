package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.ClienteDTO;
import br.com.contadora.contadora_api.model.Cliente.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO paraDTO(Cliente cliente);

    Cliente paraEntidade(ClienteDTO dto);
}