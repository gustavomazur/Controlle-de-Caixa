package br.com.contadora.contadora_api.builder;

import br.com.contadora.contadora_api.dto.ItemVendaRequest;
import br.com.contadora.contadora_api.dto.VendaRequest;
import br.com.contadora.contadora_api.model.tipo.TipoDeVenda;
import net.datafaker.Faker;
import java.math.BigDecimal;
import java.util.List;

public class VendaBuilder {

    private static final Faker faker = new Faker(new java.util.Locale("pt-BR"));

    private String clienteNome = faker.name().fullName();
    private String vendedor = faker.name().fullName();
    private TipoDeVenda tipoPagamento = TipoDeVenda.Dinheiro;
    private BigDecimal desconto = BigDecimal.ZERO;
    private List<ItemVendaRequest> itens = List.of(
            new ItemVendaRequest("Produto Teste", "1234567890123", 1, BigDecimal.valueOf(50))
    );

    public static VendaBuilder umaVenda() {
        return new VendaBuilder();
    }

    public VendaBuilder comClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
        return this;
    }

    public VendaBuilder comTipoPagamento(TipoDeVenda tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
        return this;
    }

    public VendaRequest build() {
        return new VendaRequest(clienteNome, vendedor, tipoPagamento, desconto, itens);
    }
}
