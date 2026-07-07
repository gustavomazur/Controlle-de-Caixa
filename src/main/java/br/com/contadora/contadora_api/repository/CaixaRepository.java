package br.com.contadora.contadora_api.repository;

import br.com.contadora.contadora_api.model.caixa.Caixa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CaixaRepository extends JpaRepository<Caixa, Long> {
    Optional<Caixa> findByUsuarioId(Long usuarioId);
}