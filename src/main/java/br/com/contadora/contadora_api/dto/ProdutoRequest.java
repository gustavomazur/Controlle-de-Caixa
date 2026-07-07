package br.com.contadora.contadora_api.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoRequest(
        Long id,
        String nome,
        Integer quantidade,
        String descricao,
        BigDecimal precoDeCompra,
        BigDecimal precoDeVenda,
        String barraDoProduto,
        @NotNull String CategoriaNome
) {
}
