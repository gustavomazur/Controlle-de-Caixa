package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.EnderecoDTO;
import br.com.contadora.contadora_api.model.endereco.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco paraEntidade(EnderecoDTO dto);
}
