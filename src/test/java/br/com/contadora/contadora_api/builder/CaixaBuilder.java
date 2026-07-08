package br.com.contadora.contadora_api.builder;

import br.com.contadora.contadora_api.model.caixa.Caixa;
import br.com.contadora.contadora_api.model.caixa.CaixaMovimentacao;
import br.com.contadora.contadora_api.model.usuario.Usuario;
import net.datafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CaixaBuilder {

    private static final Faker faker = new Faker(new java.util.Locale("pt-BR"));

    public static Caixa umCaixa(Usuario usuario) {
        Caixa caixa = new Caixa();
        caixa.setSaldo(BigDecimal.ZERO);
        caixa.setUsuario(usuario);
        return caixa;
    }

    public static CaixaMovimentacao umaMovimentacao(Caixa caixa, String tipo) {
        CaixaMovimentacao m = new CaixaMovimentacao();
        m.setTipo(tipo);
        m.setValor(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 500)));
        m.setDescricao(faker.lorem().sentence());
        m.setDataHora(LocalDateTime.now());
        m.setCaixa(caixa);
        return m;
    }
}
