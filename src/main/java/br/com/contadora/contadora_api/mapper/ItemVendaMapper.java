package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.ItemVendaDTO;
import br.com.contadora.contadora_api.dto.ItemVendaRequest;
import br.com.contadora.contadora_api.model.venda.ItemVenda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemVendaMapper {

    // Request → Entidade (campos simples)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produto", ignore = true)           // service busca no banco
    @Mapping(target = "lucroDoItem", ignore = true)       // service calcula
    @Mapping(target = "precoDeCompraNoMomento", ignore = true) // service seta
    @Mapping(target = "venda", ignore = true)             // service seta
    ItemVenda paraEntidade(ItemVendaRequest request);

    // Entidade → DTO (saída pro front)
    @Mapping(source = "produto.nome", target = "produtoNome")
    ItemVendaDTO paraDTO(ItemVenda item);
}