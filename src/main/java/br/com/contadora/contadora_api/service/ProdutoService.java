package br.com.contadora.contadora_api.service;

import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.dto.ProdutoRequest;
import br.com.contadora.contadora_api.mapper.ProdutoMapper;
import br.com.contadora.contadora_api.model.Produto.Produto;
import br.com.contadora.contadora_api.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {

    //listar


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    public ProdutoDTO cadastrar(ProdutoRequest request) {


        if ((request.barraDoProduto() == null || request.barraDoProduto().isBlank()) &&
                (request.nome() == null || request.nome().isBlank())) {
            throw new IllegalArgumentException("Deve informar a barra do produto ou o nome");
        }
        if (request.categoria() == null || request.categoria().isBlank()) {
            throw new IllegalArgumentException("Deve informar a categoria do produto");
        }
        if (request.precoDeCompra() == null || request.precoDeCompra().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de compra inválido");
        }
        if (request.precoDeVenda() == null || request.precoDeVenda().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de venda inválido");
        }

        Produto produto = produtoMapper.paraEntidade(request);
        produtoRepository.save(produto);
        return produtoMapper.paraDTO(produto);
    }

    public ProdutoDTO atualizar(ProdutoRequest request) {

        if ((request.barraDoProduto() == null || request.barraDoProduto().isBlank()) &&
            (request.nome() == null || request.nome().isBlank())) {
            throw new IllegalArgumentException("Deve informar a barra do produto ou o nome");
        }

        Produto produto;

        if (request.barraDoProduto() != null && !request.barraDoProduto().isBlank()) {
            produto = produtoRepository.findByBarraDoProduto(request.barraDoProduto())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não encontrado pelo barra: " + request.barraDoProduto()));

        } else {
            produto = produtoRepository.findByNome(request.nome())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não econtraodo pela nome: " + request.nome()));
        }

        produto.setQuantidade(request.quantidade());
        produto.setPrecoDeVenda(request.precoDeVenda());
        produto.setPrecoDeCompra(request.precoDeCompra());

        produto = produtoRepository.save(produto);
        return produtoMapper.paraDTO(produto);
    }

    public ProdutoDTO deletar(ProdutoRequest request) {

        if ((request.barraDoProduto() == null || request.barraDoProduto().isBlank()) &&
                (request.nome() == null || request.nome().isBlank())) {
            throw new IllegalArgumentException("Deve informar a barra do produto ou o nome");
        }

        Produto produto;

        if (request.barraDoProduto() != null && !request.barraDoProduto().isBlank()) {
            produto = produtoRepository.findByBarraDoProduto(request.barraDoProduto())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não encontrado pelo barra: " + request.barraDoProduto()));

        } else {
            produto = produtoRepository.findByNome(request.nome())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto não econtraodo pelo nome: " + request.nome()));
        }
        produtoRepository.delete(produto);
        return produtoMapper.paraDTO(produto);


    }
}