package br.com.contadora.contadora_api.controller;

import br.com.contadora.contadora_api.model.caixa.CaixaMovimentacao;
import br.com.contadora.contadora_api.model.usuario.Usuario;
import br.com.contadora.contadora_api.service.CaixaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/caixa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CaixaController {

    private final CaixaService service;

    public CaixaController(CaixaService service) {
        this.service = service;
    }

    @GetMapping("/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        BigDecimal saldo = service.consultarSaldo(usuario);
        return ResponseEntity.ok(saldo);
    }

    @PostMapping("/entrada")
    public ResponseEntity<CaixaMovimentacao> entrada(@RequestBody MovimentacaoRequest request) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        CaixaMovimentacao movimentacao = service.entrada(request.valor(), request.descricao(), usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacao);
    }

    @PostMapping("/saida")
    public ResponseEntity<CaixaMovimentacao> saida(@RequestBody MovimentacaoRequest request) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        CaixaMovimentacao movimentacao = service.saida(request.valor(), request.descricao(), usuario);
        return ResponseEntity.ok(movimentacao);
    }

    @GetMapping("/historico")
    public ResponseEntity<List<CaixaMovimentacao>> historico(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        List<CaixaMovimentacao> movimentacoes = service.historico(dataInicio, dataFim, usuario);
        return ResponseEntity.ok(movimentacoes);
    }

    private record MovimentacaoRequest(BigDecimal valor, String descricao) {}
}
