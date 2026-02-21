package br.com.contadora.contadora_api.dto;

import jakarta.validation.constraints.*;

public record DadosCadastrarProdutoDTO(

        Long id, @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @Min(0)
        int quantidade,

        @NotBlank
        String categoria,

        @PositiveOrZero
        double precoquepaguei,

        @Positive
        double precodevenda,

        @NotBlank
        String imagem

) {
}
