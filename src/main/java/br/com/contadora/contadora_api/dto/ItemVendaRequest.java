package br.com.contadora.contadora_api.dto;

import java.math.BigDecimal;

public record ItemVendaRequest(
        String produtoNome,
        String barraDoProduto,
        Integer quantidade,
        BigDecimal precoVendido
) {
}
