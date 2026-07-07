package br.com.contadora.contadora_api.service;

import br.com.contadora.contadora_api.dto.CategoriaDTO;
import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.dto.ProdutoRequest;
import br.com.contadora.contadora_api.mapper.ProdutoMapper;
import br.com.contadora.contadora_api.model.Produto.Categoria;
import br.com.contadora.contadora_api.model.Produto.Produto;
import br.com.contadora.contadora_api.repository.CategoriaRepository;
import br.com.contadora.contadora_api.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {


    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoMapper = produtoMapper;
    }
    public Page<ProdutoDTO> listarTodos(Pageable pageable) {
        return produtoRepository.findAll(pageable)
                .map(produtoMapper::paraDTO);

    }
    public Page<ProdutoDTO> listarCategoria(String nome, Pageable pageable) {
        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(nome)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada" + nome));
        return produtoRepository.findByCategoria(categoria, pageable)
                .map(produtoMapper::paraDTO);
    }
    public Page<CategoriaDTO> listarCategorias(Pageable pageable) {
        return categoriaRepository.findAll(pageable)
                .map(categoria -> new CategoriaDTO(categoria.getNome()));

    }

    public CategoriaDTO criarCategoria(CategoriaDTO dto) {
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório");
        }
        if (categoriaRepository.findByNomeIgnoreCase(dto.nome()).isPresent()) {
            throw new IllegalArgumentException("Categoria já existe: " + dto.nome());
        }

        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoriaRepository.save(categoria);
        return new CategoriaDTO(categoria.getNome());
    }
    public ProdutoDTO cadastrar(ProdutoRequest request) {

        if ((request.barraDoProduto() == null || request.barraDoProduto().isBlank()) &&
                (request.nome() == null || request.nome().isBlank())) {
            throw new IllegalArgumentException("Deve informar a barra do produto ou o nome");

        }
        if (request.CategoriaNome() == null || request.CategoriaNome().isBlank()) {
            throw new IllegalArgumentException("Deve informar a categoria do produto");
        }
        if (request.precoDeCompra() == null || request.precoDeCompra().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de compra inválido");
        }
        if (request.precoDeVenda() == null || request.precoDeVenda().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Preço de venda inválido");
        }

        Categoria categoria = categoriaRepository.findByNomeIgnoreCase(request.CategoriaNome())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada: " + request.CategoriaNome()));

        Produto produto = produtoMapper.paraEntidade(request);
        produto.setCategoria(categoria);
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