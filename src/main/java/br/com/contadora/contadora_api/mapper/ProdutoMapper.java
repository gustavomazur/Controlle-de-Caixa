package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.model.Produto.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {


    ProdutoDTO paraDTO(Produto produto);

    Produto paraEntidade(ProdutoDTO dto);
}