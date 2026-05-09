package br.com.contadora.contadora_api.service;

import br.com.contadora.contadora_api.model.caixa.Caixa;
import br.com.contadora.contadora_api.model.caixa.CaixaMovimentacao;
import br.com.contadora.contadora_api.repository.CaixaMovimentacaoRepository;
import br.com.contadora.contadora_api.repository.CaixaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private CaixaMovimentacaoRepository caixaMovimentacaoRepository;

    public BigDecimal consultarSaldo() {
        Caixa caixa = caixaRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Caixa não encontrado"));
        return caixa.getSaldo();
    }

    @Transactional
    public CaixaMovimentacao entrada(BigDecimal valor, String descricao) {
        Caixa caixa = caixaRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Caixa não encontrado"));

        caixa.setSaldo(caixa.getSaldo().add(valor));
        caixaRepository.save(caixa);

        CaixaMovimentacao movimentacao = new CaixaMovimentacao();
        movimentacao.setTipo("ENTRADA");
        movimentacao.setValor(valor);
        movimentacao.setDescricao(descricao);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setCaixa(caixa);

        return caixaMovimentacaoRepository.save(movimentacao);
    }

    @Transactional
    public CaixaMovimentacao saida(BigDecimal valor, String descricao) {
        Caixa caixa = caixaRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Caixa não encontrado"));

        if (caixa.getSaldo().compareTo(valor) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        caixa.setSaldo(caixa.getSaldo().subtract(valor));
        caixaRepository.save(caixa);

        CaixaMovimentacao movimentacao = new CaixaMovimentacao();
        movimentacao.setTipo("SAIDA");
        movimentacao.setValor(valor);
        movimentacao.setDescricao(descricao);
        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setCaixa(caixa);

        return caixaMovimentacaoRepository.save(movimentacao);
    }

    public List<CaixaMovimentacao> historico(LocalDateTime inicio, LocalDateTime fim) {
        return caixaMovimentacaoRepository.findByDataHoraBetween(inicio, fim);
    }
}
