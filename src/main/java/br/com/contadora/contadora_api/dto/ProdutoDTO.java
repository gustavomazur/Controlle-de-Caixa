package br.com.contadora.contadora_api.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        Integer quantidade,
        String categoria,
        String descricao,
        BigDecimal precoCompra,
        BigDecimal precoVenda,
        String imagem,
        String barraDoProduto

) {
}
