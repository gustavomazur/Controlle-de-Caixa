package br.com.contadora.contadora_api.controller;

import br.com.contadora.contadora_api.dto.ClienteDTO;
import br.com.contadora.contadora_api.dto.ProdutoDTO;
import br.com.contadora.contadora_api.dto.ProdutoRequest;
import br.com.contadora.contadora_api.model.Cliente.Cliente;
import br.com.contadora.contadora_api.model.Produto.Produto;
import br.com.contadora.contadora_api.service.ClienteService;
import br.com.contadora.contadora_api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    //mecher
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
        ProdutoDTO produto = service.atulizar(DTO);
        return ResponseEntity.ok(produto);
    }
    @DeleteMapping
    public ResponseEntity<ProdutoDTO> deletar(@Valid @RequestBody ProdutoRequest DTO) {
        ProdutoDTO produto = service.deletar(DTO);
        return ResponseEntity.ok(produto);
    }

}
