package br.com.contadora.contadora_api.model.lucro;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Table(name = "lucroDoDia")
@Entity(name = "LucroDoDia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class LucroDoDia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer lucro;

}
