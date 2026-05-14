package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.ItemVendaDTO;
import br.com.contadora.contadora_api.model.venda.ItemVenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper {

    @Mapping(source = "produto.nome", target = "produtoNome")
    ItemVendaDTO paraDTO(ItemVenda item);
}