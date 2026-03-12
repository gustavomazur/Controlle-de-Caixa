package br.com.contadora.contadora_api.repository;
import br.com.contadora.contadora_api.model.venda.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {
}

