package br.com.contadora.contadora_api.controller;


import br.com.contadora.contadora_api.dto.DadosAtulizarProdutoDTO;
import br.com.contadora.contadora_api.dto.DadosCadastrarProdutoDTO;
import br.com.contadora.contadora_api.dto.DadosListagemProdutoDTO;
import br.com.contadora.contadora_api.dto.VendaResumoDTO;
import br.com.contadora.contadora_api.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import br.com.contadora.contadora_api.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.com.contadora.contadora_api.repository.ProdutoRepository;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoRepository respository;

    @PostMapping
    public void cadastrarProduto(@RequestBody @Valid DadosCadastrarProdutoDTO dados) {
        respository.save(new Produto(dados));
    }
    @GetMapping
    public List<DadosListagemProdutoDTO> listar() {
        return respository.findAll().stream().map(DadosListagemProdutoDTO::new).toList();
    }
    @PutMapping
    @Transactional
    public void atulizarProduto(@RequestBody @Valid DadosAtulizarProdutoDTO dados) {
        var produt = respository.getReferenceById(dados.id());
        produt.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirProduto(@PathVariable Long id) {
        respository.deleteById(id);
    }
    @GetMapping("/lucro-total")
    public double lucroTotal() {
        return service.lucroTotalEstoque();
    }
    @GetMapping("/lucro-unitario/{id}")
    public double lucroUnitario(@PathVariable Long id) {
        var produt = respository.getReferenceById(id);
        return produt.calcularLucroUnitario();
    }
    @PostMapping("/{id}/vender/{quantidade}")
    public VendaResumoDTO vender(
            @PathVariable Long id,
            @PathVariable int quantidade) {

        return service.vender(id, quantidade);
    }

}
