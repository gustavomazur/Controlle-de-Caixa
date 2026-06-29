package br.com.contadora.contadora_api.dto;

import br.com.contadora.contadora_api.model.tipo.TipoDeVenda;

import java.math.BigDecimal;
import java.util.List;

public record VendaRequest(

  String clienteNome,
  String vendedor,
  TipoDeVenda tipoPagamento,
  BigDecimal desconto,
  List<ItemVendaRequest> itens
) {
}
