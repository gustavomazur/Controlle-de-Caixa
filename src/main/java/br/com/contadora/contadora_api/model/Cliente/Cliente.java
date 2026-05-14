package br.com.contadora.contadora_api.model.Cliente;
import br.com.contadora.contadora_api.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "cliente")
@Entity(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false, unique = true)
    private String telefone;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String tamanho;

    @Column(length = 1000)
    private String foto;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private List<Endereco> enderecos;

}
