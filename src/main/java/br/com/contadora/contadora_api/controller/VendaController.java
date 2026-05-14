package br.com.contadora.contadora_api.controller;


import br.com.contadora.contadora_api.dto.VendaDTO;
import br.com.contadora.contadora_api.model.venda.Venda;
import br.com.contadora.contadora_api.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/venda")

public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }
    @PostMapping
    public ResponseEntity<Venda> registrarVenda(@RequestBody VendaDTO DTO) {
        vendaService.registrarVenda(DTO);
        return ResponseEntity.ok().build();
    }



}


