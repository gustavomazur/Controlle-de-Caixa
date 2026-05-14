package br.com.contadora.contadora_api.model.caixa;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Table(name = "caixa")
@Entity(name = "Caixa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo;
}