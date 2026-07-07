package br.com.contadora.contadora_api.dto;

public record EnderecoDTO(
        String nome,
        String cep,
        String rua,
        String numero,
        String referencia
) {
}
