package br.com.contadora.contadora_api.controller;

import br.com.contadora.contadora_api.dto.ClienteDTO;
import br.com.contadora.contadora_api.dto.EnderecoDTO;
import br.com.contadora.contadora_api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {


    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> clientes = service.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ClienteDTO> buscarPorNome(@PathVariable String nome) {
        ClienteDTO cliente = service.findByNome(nome);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criar(@RequestBody @Valid ClienteDTO clienteDTO) {
        ClienteDTO clienteSalva = service.insert(clienteDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clienteSalva.id()).toUri();
        return ResponseEntity.created(uri).body(clienteSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = service.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<ClienteDTO> criarEndereco(
            @PathVariable Long id,
            @RequestBody EnderecoDTO enderecoDTO) {
        ClienteDTO cliente = service.criarEndereco(id, enderecoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cliente.id()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping("/{id}/enderecos/{enderecoId}")
    public ResponseEntity<ClienteDTO> atualizarEndereco(
            @PathVariable Long id,
            @PathVariable Long enderecoId,
            @RequestBody EnderecoDTO enderecoDTO) {
        ClienteDTO cliente = service.atualizarEndereco(id, enderecoId, enderecoDTO);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}



