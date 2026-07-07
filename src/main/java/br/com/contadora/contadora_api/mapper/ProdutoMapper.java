package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.CategoriaDTO;
import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.dto.ProdutoRequest;
import br.com.contadora.contadora_api.model.Produto.Categoria;
import br.com.contadora.contadora_api.model.Produto.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(target = "categoria", source = "categoria")
    ProdutoDTO paraDTO(Produto produto);

    CategoriaDTO toCategoriaDTO(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    Produto paraEntidade(ProdutoRequest dto);
}