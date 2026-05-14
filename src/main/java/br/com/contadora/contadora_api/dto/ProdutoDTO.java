package br.com.contadora.contadora_api.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        Integer quantidade,
        String categoria,
        String descricao,
        BigDecimal precoDeCompra,
        BigDecimal precoDeVenda,
        String barraDoProduto,
        String imagem
) {
}
