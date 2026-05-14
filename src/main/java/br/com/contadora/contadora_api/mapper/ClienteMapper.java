package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.ClienteDTO;
import br.com.contadora.contadora_api.model.Cliente.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "enderecos", target = "endereco")
    ClienteDTO paraDTO(Cliente cliente);

    @Mapping(source = "endereco", target = "enderecos")
    Cliente paraEntidade(ClienteDTO dto);
}