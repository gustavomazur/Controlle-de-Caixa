package br.com.contadora.contadora_api.model.venda;

import br.com.contadora.contadora_api.model.Produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "item_venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        @ManyToOne
        @JoinColumn(name = "produto_id")
        private Produto produto;

        @Column(nullable = false)
        private Integer quantidade;

        @Column(nullable = false)
        private BigDecimal precoVendido;

        @Column(nullable = false)
        private BigDecimal lucroDoItem;

        @Column(nullable = false)
        private BigDecimal precoDeCompraNoMomento;

        @Column(nullable = false)
        @ManyToOne
        @JoinColumn(name = "venda_id")
        private Venda venda;

        }


