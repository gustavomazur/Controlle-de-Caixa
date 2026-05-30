package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.dto.ProdutoRequest;
import br.com.contadora.contadora_api.model.Produto.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO paraDTO(Produto produto);

    @Mapping(target = "id", ignore = true)
    Produto paraEntidade(ProdutoRequest dto);

    public Produto paraEntidade(Produto produto);


}