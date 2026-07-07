package br.com.contadora.contadora_api.model.Produto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "produto")
@Entity(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;
    private Integer quantidade;
    private String descricao;
    private BigDecimal precoDeCompra;
    private BigDecimal precoDeVenda;

    @Column(unique = true)
    private String barraDoProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_nome", nullable = false)
    private Categoria categoria;
}







