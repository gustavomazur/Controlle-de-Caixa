package br.com.contadora.contadora_api.model;

import br.com.contadora.contadora_api.dto.DadosAtulizarProdutoDTO;
import br.com.contadora.contadora_api.dto.DadosCadastrarProdutoDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Table(name ="produto")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private int quantidade;
    private String categoria;
    private double precoquepaguei;
    private double precodevenda;
    private String imagem;


    public void atualizarInformacoes(@Valid DadosAtulizarProdutoDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.quantidade() != 0) {
            this.quantidade = dados.quantidade();
        }
        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
        if (dados.precoquepaguei() != 0) {
            this.precoquepaguei = dados.precoquepaguei();
        }
        if (dados.precodevenda() != 0) {
            this.precodevenda = dados.precodevenda();
        }
        if (dados.imagem() != null) {
            this.imagem = dados.imagem();
        }
        }
    public double calcularLucro() {
        return (precodevenda * quantidade);
    }
    public double calcularLucroUnitario() {
        return precodevenda - precoquepaguei;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
