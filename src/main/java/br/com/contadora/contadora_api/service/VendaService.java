package br.com.contadora.contadora_api.service;

import br.com.contadora.contadora_api.dto.ItemVendaRequest;
import br.com.contadora.contadora_api.dto.VendaDTO;
import br.com.contadora.contadora_api.dto.VendaRequest;
import br.com.contadora.contadora_api.mapper.ItemVendaMapper;
import br.com.contadora.contadora_api.mapper.VendaMapper;
import br.com.contadora.contadora_api.model.Cliente.Cliente;
import br.com.contadora.contadora_api.model.Produto.Produto;
import br.com.contadora.contadora_api.model.caixa.Caixa;
import br.com.contadora.contadora_api.model.venda.ItemVenda;
import br.com.contadora.contadora_api.model.venda.Venda;
import br.com.contadora.contadora_api.repository.CaixaRepository;
import br.com.contadora.contadora_api.repository.ClienteRepository;
import br.com.contadora.contadora_api.repository.ProdutoRepository;
import br.com.contadora.contadora_api.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CaixaRepository caixaRepository;

    @Autowired
    private VendaMapper vendaMapper;

    @Autowired
    private ItemVendaMapper itemVendaMapper;

    public VendaDTO registrarVenda(VendaRequest request) {

        if (request.itens() == null || request.itens().isEmpty()) {
            throw new IllegalArgumentException("Venda deve conter pelo menos um item");
        }
        if (request.clienteNome() == null || request.clienteNome().isBlank()) {
            throw new IllegalArgumentException("Cliente deve ter um nome");
        }
        if (request.vendedor() == null || request.vendedor().isBlank()) {
            throw new IllegalArgumentException("Vendedor deve ser informado");
        }
        if (request.tipoPagamento() == null) {
            throw new IllegalArgumentException("Tipo de pagamento deve ser informado");
        }
        Venda venda = vendaMapper.paraEntidade(request);

        Cliente cliente = clienteRepository.findByNome(request.clienteNome())
                .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));


        venda.setCliente(cliente);
        venda.setData(LocalDateTime.now());
        venda.setValorTotal(BigDecimal.ZERO);
        venda.setLucroTotal(BigDecimal.ZERO);
        venda.setDesconto(BigDecimal.ZERO);
        venda.setItens(new ArrayList<>());

        for (ItemVendaRequest itemRequest : request.itens()) {

            Produto produto = buscarProduto(itemRequest);

            if (itemRequest.quantidade() == null || itemRequest.quantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade iválida para" + produto.getNome());
            }
            if (itemRequest.quantidade() > produto.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente " + produto.getNome());
            }
            //precoVendido == null
            if (itemRequest.precoVendido() == null ) {
                throw new IllegalArgumentException("Preço vendido não informado para " + produto.getNome());
            }

            BigDecimal quantidade = BigDecimal.valueOf(itemRequest.quantidade());
            BigDecimal precoDeCompraNoMomento = produto.getPrecoDeCompra();

            BigDecimal lucroDoItem = produto.getPrecoDeCompra()
                    .subtract(precoDeCompraNoMomento)
                    .multiply(quantidade);

            BigDecimal descontoItem = produto.getPrecoDeCompra()
                    .subtract(itemRequest.precoVendido())
                    .multiply(quantidade);

            BigDecimal valorTotalItem = itemRequest.precoVendido().multiply(quantidade);

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setProduto(produto);
            itemVenda.setQuantidade(itemRequest.quantidade());
            itemVenda.setPrecoVendido(itemRequest.precoVendido());
            itemVenda.setPrecoDeCompraNoMomento(precoDeCompraNoMomento);
            itemVenda.setLucroDoItem(lucroDoItem);
            itemVenda.setVenda(venda);

            venda.setValorTotal(venda.getValorTotal().add(valorTotalItem));
            venda.setLucroTotal(venda.getLucroTotal().add(lucroDoItem));
            venda.setDesconto(venda.getDesconto().add(descontoItem));
            venda.getItens().add(itemVenda);

            produto.setQuantidade(produto.getQuantidade() - itemRequest.quantidade());

            if (produto.getQuantidade() == 0) {
                produtoRepository.delete(produto);
            } else {
                produtoRepository.save(produto);
            }
        }

        Caixa caixa = caixaRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("Caixa não encotrando"));

        caixa.setSaldo(caixa.getSaldo().add(venda.getValorTotal()));
        caixaRepository.save(caixa);

        Venda vendaSalva = vendaRepository.save(venda);


        return vendaMapper.paraDTO(vendaSalva);
    }
    private Produto buscarProduto(ItemVendaRequest itemRequest) {
        if (itemRequest.produtoNome() != null && !itemRequest.produtoNome().isBlank()) {
            return produtoRepository.findByNome(itemRequest.produtoNome())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não encontrado pelo nome: " + itemRequest.produtoNome()));
        }
        if (itemRequest.barraDoProduto() != null && !itemRequest.barraDoProduto().isBlank()) {
            return produtoRepository.findByBarraDoProduto(itemRequest.barraDoProduto())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não encontrado pela barra: " + itemRequest.barraDoProduto()));
        }
        throw new IllegalArgumentException("Informe o nome ou a barra do produto");
    }
}
