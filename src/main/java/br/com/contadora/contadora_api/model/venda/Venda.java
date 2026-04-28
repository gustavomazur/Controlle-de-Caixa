package br.com.contadora.contadora_api.model.venda;

import br.com.contadora.contadora_api.model.Cliente.Cliente;
import br.com.contadora.contadora_api.model.tipo.TipoDeVenda;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "venda")
@Entity(name = "Venda")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Venda {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime data;

        @ManyToOne
        private Cliente cliente;

        private String vendedor;

        @Enumerated(EnumType.STRING)
        private TipoDeVenda tipoPagamento;

        private BigDecimal desconto;

        private BigDecimal valorTotal;

        private BigDecimal lucroTotal;

        @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
        private List<ItemVenda> itens;



        }



