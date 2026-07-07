package br.com.contadora.contadora_api.repository;

import br.com.contadora.contadora_api.model.Produto.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {

    Optional<Categoria> findByNomeIgnoreCase(String nome);
}
