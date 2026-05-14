package br.com.contadora.contadora_api.dto;

import br.com.contadora.contadora_api.model.tipo.TipoDeVenda;

import java.util.List;

public record VendaRequest(

  String clienteNome,
  String vendedor,
  TipoDeVenda tipoPagamento,
  List<ItemVendaRequest> itens
) {
}
