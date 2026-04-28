package br.com.contadora.contadora_api.dto;

import br.com.contadora.contadora_api.model.tipo.TipoDeVenda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaDTO(
        Long id,
        LocalDateTime data,
        Long clienteId,
        String clienteNome,
        String vendedor,
        TipoDeVenda tipoPagamento,
        BigDecimal desconto,
        BigDecimal valorTotal,
        BigDecimal lucroTotal,
        List<ItemVendaDTO> itens
) {}
