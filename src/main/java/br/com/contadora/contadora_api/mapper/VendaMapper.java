package br.com.contadora.contadora_api.mapper;

import br.com.contadora.contadora_api.dto.VendaDTO;
import br.com.contadora.contadora_api.dto.VendaRequest;
import br.com.contadora.contadora_api.model.venda.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemVendaMapper.class})
public interface VendaMapper {

    @Mapping(source = "cliente.nome", target = "clienteNome")
    VendaDTO paraDTO(Venda venda);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    @Mapping(target = "lucroTotal", ignore = true)
    @Mapping(target = "desconto", ignore = true)
    @Mapping(target = "data", ignore = true)
    Venda paraEntidade(VendaRequest request);
}
