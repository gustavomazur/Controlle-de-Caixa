package br.com.contadora.contadora_api.builder;

import br.com.contadora.contadora_api.dto.ProdutoRequest;
import net.datafaker.Faker;
import java.math.BigDecimal;

public class ProdutoBuilder {

    private static final Faker faker = new Faker(new java.util.Locale("pt-BR"));

    private String nome = faker.commerce().productName();
    private Integer quantidade = faker.number().numberBetween(1, 100);
    private String descricao = faker.lorem().sentence();
    private BigDecimal precoDeCompra = BigDecimal.valueOf(faker.number().randomDouble(2, 1, 50));
    private BigDecimal precoDeVenda = BigDecimal.valueOf(faker.number().randomDouble(2, 50, 200));
    private String barraDoProduto = faker.code().ean13();
    private String categoriaNome = "Bebidas";

    public static ProdutoBuilder umProduto() {
        return new ProdutoBuilder();

    }

    public ProdutoBuilder comNome(String nome) {
        this.categoriaNome = categoriaNome;
        return this;

    }
    public ProdutoRequest build() {
        return new ProdutoRequest(null, nome, quantidade, descricao,
                precoDeCompra, precoDeVenda, barraDoProduto, categoriaNome);
    }
}