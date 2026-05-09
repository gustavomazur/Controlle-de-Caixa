package br.com.contadora.contadora_api.model.caixa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "caixa_movimentacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CaixaMovimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;

    private BigDecimal valor;

    private String descricao;

    private LocalDateTime dataHora;
    @ManyToOne
    private Caixa caixa;


}
