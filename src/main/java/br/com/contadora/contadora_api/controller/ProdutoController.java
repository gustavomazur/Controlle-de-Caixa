package br.com.contadora.contadora_api.controller;

import br.com.contadora.contadora_api.dto.CategoriaDTO;
import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.dto.ProdutoRequest;
import br.com.contadora.contadora_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrar(@Valid @RequestBody ProdutoRequest DTO) {
        ProdutoDTO produto = service.cadastrar(DTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @RequestBody ProdutoRequest DTO) {
        ProdutoDTO produto = service.atualizar(DTO);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping
    public ResponseEntity<ProdutoDTO> deletar(@Valid @RequestBody ProdutoRequest DTO) {
        ProdutoDTO produto = service.deletar(DTO);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ProdutoDTO>> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return ResponseEntity.ok(service.listarTodos(pageable));
    }

    @PostMapping("/categoria")
    public ResponseEntity<CategoriaDTO> criarCategoria(@Valid @RequestBody CategoriaDTO dto) {
        CategoriaDTO categoria = service.criarCategoria(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
    }

    @GetMapping("/categoria")
    public ResponseEntity<Page<CategoriaDTO>> listarCategorias(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return ResponseEntity.ok(service.listarCategorias(pageable));
    }

    @GetMapping("/categoria/{nomeCategoria}")
    public ResponseEntity<Page<ProdutoDTO>> listarPorCategoria(
            @PathVariable String nomeCategoria,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        return ResponseEntity.ok(service.listarCategoria(nomeCategoria, pageable));
    }
}
