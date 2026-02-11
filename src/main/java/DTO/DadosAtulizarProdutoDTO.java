package DTO;

import jakarta.validation.constraints.NotNull;

public record DadosAtulizarProdutoDTO(
        @NotNull
        Long id,
        String nome,
        String descricao,
        int quantidade,
        String categoria,
        double precoquepaguei,
        double precodevenda,
        String imagem) {

}
