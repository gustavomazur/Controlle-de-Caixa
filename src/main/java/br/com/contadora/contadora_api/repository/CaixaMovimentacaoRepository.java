package br.com.contadora.contadora_api.repository;

import br.com.contadora.contadora_api.model.caixa.CaixaMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CaixaMovimentacaoRepository extends JpaRepository<CaixaMovimentacao, Long> {
    List<CaixaMovimentacao> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
    List<CaixaMovimentacao> findAllByOrderByDataHoraDesc();
}
