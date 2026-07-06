package br.com.contadora.contadora_api.controller;


import br.com.contadora.contadora_api.dto.VendaDTO;
import br.com.contadora.contadora_api.dto.VendaRequest;
import br.com.contadora.contadora_api.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VendaController {
    //mecher
    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }
    @PostMapping
    public ResponseEntity<VendaDTO> registrarVenda(@RequestBody VendaRequest DTO) {
        return ResponseEntity.ok(vendaService.registrarVenda(DTO));
    }

}


