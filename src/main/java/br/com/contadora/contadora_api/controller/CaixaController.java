package br.com.contadora.contadora_api.controller;

import br.com.contadora.contadora_api.model.caixa.CaixaMovimentacao;
import br.com.contadora.contadora_api.service.CaixaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/caixa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CaixaController {

    //analisar
    private final CaixaService service;

    public CaixaController(CaixaService service) {
        this.service = service;
    }

    @GetMapping("/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo() {
        BigDecimal saldo = service.consultarSaldo();
        return ResponseEntity.ok(saldo);
    }

    @PostMapping("/entrada")
    public ResponseEntity<CaixaMovimentacao> entrada(@RequestBody MovimentacaoRequest request) {
        CaixaMovimentacao movimentacao = service.entrada(request.valor(), request.descricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacao);
    }

    @PostMapping("/saida")
    public ResponseEntity<CaixaMovimentacao> saida(@RequestBody MovimentacaoRequest request) {
        CaixaMovimentacao movimentacao = service.saida(request.valor(), request.descricao());
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<CaixaMovimentacao>> historico(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        List<CaixaMovimentacao> movimentacoes = service.historico(dataInicio, dataFim);
        return ResponseEntity.ok(movimentacoes);
    }

    private record MovimentacaoRequest(BigDecimal valor, String descricao) {}
}
