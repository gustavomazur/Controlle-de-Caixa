package br.com.contadora.contadora_api.model.lucro;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private BigDecimal lucro;

}
