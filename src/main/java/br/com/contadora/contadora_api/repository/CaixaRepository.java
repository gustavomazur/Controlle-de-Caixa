package br.com.contadora.contadora_api.repository;

import br.com.contadora.contadora_api.model.caixa.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
}