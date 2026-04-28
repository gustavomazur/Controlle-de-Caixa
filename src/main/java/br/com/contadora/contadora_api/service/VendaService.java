package br.com.contadora.contadora_api.service;

import br.com.contadora.contadora_api.model.Produto.Produto;
import br.com.contadora.contadora_api.model.venda.ItemVenda;
import br.com.contadora.contadora_api.model.venda.Venda;
import br.com.contadora.contadora_api.repository.ClienteRepository;
import br.com.contadora.contadora_api.repository.ProdutoRepository;
import br.com.contadora.contadora_api.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Venda registrarVenda(Venda venda) {

        // Validações da venda em si
        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("A venda deve ter ao menos um item");
        }
        if (venda.getCliente() == null || venda.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente não informado");
        }
        if (!clienteRepository.existsById(venda.getCliente().getId())) {
            throw new EntityNotFoundException("Cliente não encontrado");
        }
        if (venda.getVendedor() == null || venda.getVendedor().isBlank()) {
            throw new IllegalArgumentException("Vendedor não informado");
        }
        if (venda.getTipoPagamento() == null) {
            throw new IllegalArgumentException("Tipo de pagamento não informado");
        }

        // Inicializa os totais
        venda.setData(LocalDateTime.now());
        venda.setValorTotal(BigDecimal.ZERO);
        venda.setLucroTotal(BigDecimal.ZERO);
        venda.setDesconto(BigDecimal.ZERO);

        for (ItemVenda itemVenda : venda.getItens()) {

            // Validações do item
            if (itemVenda.getProduto() == null || itemVenda.getProduto().getId() == null) {
                throw new EntityNotFoundException("Produto não informado no item");
            }

            Produto produto = produtoRepository.findById(itemVenda.getProduto().getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não encontrado: " + itemVenda.getProduto().getId()));

            if (itemVenda.getQuantidade() == null || itemVenda.getQuantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade inválida para: " + produto.getNome());
            }
            if (itemVenda.getQuantidade() > produto.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente para: " + produto.getNome());
            }
            if (itemVenda.getPrecoVendido() == null) {
                throw new IllegalArgumentException("Preço vendido não informado para: " + produto.getNome());
            }

            // Congela o preço de compra no momento da venda
            itemVenda.setPrecoDeCompraNoMomento(produto.getPrecoDeCompra());

            BigDecimal quantidade = BigDecimal.valueOf(itemVenda.getQuantidade());

            // Lucro do item: (precoVendido - precoDeCompraNoMomento) * quantidade
            BigDecimal lucroItem = itemVenda.getPrecoVendido()
                    .subtract(itemVenda.getPrecoDeCompraNoMomento())
                    .multiply(quantidade);

            // Desconto: diferença entre preço padrão e preço vendido * quantidade
            // positivo = deu desconto, negativo = vendeu acima do preço
            BigDecimal descontoItem = produto.getPrecoDeVenda()
                    .subtract(itemVenda.getPrecoVendido())
                    .multiply(quantidade);

            // Valor que entrou no caixa por esse item
            BigDecimal valorItem = itemVenda.getPrecoVendido().multiply(quantidade);

            // Seta os dados no item
            itemVenda.setLucroDoItem(lucroItem);
            itemVenda.setVenda(venda);

            // Acumula os totais na venda
            venda.setValorTotal(venda.getValorTotal().add(valorItem));
            venda.setLucroTotal(venda.getLucroTotal().add(lucroItem));
            venda.setDesconto(venda.getDesconto().add(descontoItem));

            // Atualiza o estoque
            produto.setQuantidade(produto.getQuantidade() - itemVenda.getQuantidade());

            if (produto.getQuantidade() == 0) {
                produtoRepository.delete(produto);
            } else {
                produtoRepository.save(produto);
            }
        }

        return vendaRepository.save(venda);
    }
}