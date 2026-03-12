package br.com.contadora.contadora_api.dto;

import java.math.BigDecimal;

public record ItemVendaDTO(
        Long produtoId,
        Integer quantidade,
        BigDecimal preco) {
}
