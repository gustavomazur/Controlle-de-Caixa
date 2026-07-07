package br.com.contadora.contadora_api.repository;

import br.com.contadora.contadora_api.model.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
